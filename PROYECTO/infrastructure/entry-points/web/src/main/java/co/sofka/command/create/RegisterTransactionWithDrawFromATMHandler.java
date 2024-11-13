package co.sofka.command.create;

import co.sofka.Account;
import co.sofka.Transaction;
import co.sofka.TransactionAccountDetail;
import co.sofka.command.dto.BankTransactionWithdrawFromATM;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.EncryptionAndDescryption;
import co.sofka.config.TokenByDinHeaders;
import co.sofka.gateway.ITransactionAccountDetailRepository;
import co.sofka.middleware.AccountNotExistException;
import co.sofka.middleware.AccountNotHaveBalanceException;
import co.sofka.middleware.ErrorDecryptingDataException;
import co.sofka.usecase.IGetAccountByNumberService;
import co.sofka.usecase.ISaveAccountService;
import co.sofka.usecase.ISaveTransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
@AllArgsConstructor
public class RegisterTransactionWithDrawFromATMHandler {

    private static final Logger logger = LoggerFactory.getLogger(RegisterTransactionWithDrawFromATMHandler.class);

    private final IGetAccountByNumberService service;

    private final ISaveTransactionService saveTransactionService;

    private final ISaveAccountService saveAccountService;

    private final ITransactionAccountDetailRepository transactionAccountDetailRepository;

    private final TokenByDinHeaders utils;

    private EncryptionAndDescryption encryptionAndDescryption;



    public ResponseMs<Transaction> withdrawFromATM(RequestMs<BankTransactionWithdrawFromATM> request) {

        ResponseMs<Transaction> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();
        responseMs.setDinError(error);

        String LlaveSimetrica = "";
        try{
            LlaveSimetrica = utils.decode(request.getDinHeader().getLlaveSimetrica());
        } catch (Exception e) {
            throw new ErrorDecryptingDataException("Error al desencriptar la LlaveSimetrica.", request.getDinHeader(),1001);
        }

        String vectorInicializacion = "";
        try{
            vectorInicializacion = utils.decode(request.getDinHeader().getVectorInicializacion());
        } catch (Exception e) {
            throw new ErrorDecryptingDataException("Error al desencriptar la vectorInicializacion.", request.getDinHeader(),1001);
        }

        logger.info("Buscando Account por numero");
        String decode = "";
        try {
            decode = encryptionAndDescryption.decryptAes(request.getDinBody().getAccountNumber(),vectorInicializacion,LlaveSimetrica);
        } catch (Exception e) {
            throw new ErrorDecryptingDataException("Error al desencriptar el numero de cuenta.", request.getDinHeader(),1001);
        }
        logger.info("Buscando Account por numero: "+decode);

        Account account = service.findByNumber(decode);

        if (account==null){
            throw new AccountNotExistException("La cuenta no existe", request.getDinHeader(),1002);
        }


        if (account.getAmount().subtract(request.getDinBody().getAmount().add(new BigDecimal(1))).intValue() < 0) {
            throw new AccountNotHaveBalanceException("No tiene saldo suficiente.", request.getDinHeader(),1003);
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionCost(new BigDecimal(1));
        transaction.setAmountTransaction(request.getDinBody().getAmount());
        transaction.setTimeStamp(LocalDateTime.now());
        transaction.setTypeTransaction("ATM");

        //Transaction save = saveTransactionService.save(transaction);


        TransactionAccountDetail transactionAccountDetail = new TransactionAccountDetail();
        transactionAccountDetail.setAccount(account);
        transactionAccountDetail.setTransaction(transaction);
        transactionAccountDetail.setTransactionRole("Supplier");

        transactionAccountDetailRepository.save(transactionAccountDetail);


        account.setAmount(account.getAmount().subtract(request.getDinBody().getAmount().add(new BigDecimal(1))));
        saveAccountService.save(account);

        responseMs.setDinBody(transaction);

        return responseMs;
    }
}
