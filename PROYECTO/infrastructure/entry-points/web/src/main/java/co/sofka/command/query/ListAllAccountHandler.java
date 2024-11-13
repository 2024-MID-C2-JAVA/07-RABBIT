package co.sofka.command.query;

import co.sofka.Account;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.EncryptionAndDescryption;
import co.sofka.config.TokenByDinHeaders;
import co.sofka.middleware.ErrorDecryptingDataException;
import co.sofka.usecase.IGetAllAccountService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ListAllAccountHandler {

    private static final Logger logger = LoggerFactory.getLogger(ListAllAccountHandler.class);

    private final IGetAllAccountService service;

    private final TokenByDinHeaders utils;

    private EncryptionAndDescryption encryptionAndDescryption;

    public ResponseMs<List<Account>> getAll(RequestMs<Void> request) {

        ResponseMs<List<Account>> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();
        responseMs.setDinError(error);

        List<Account> all = service.getAll();

        all=all.stream().map(account -> {

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

            account.setNumber(encryptionAndDescryption.encriptAes(account.getNumber(),vectorInicializacion,LlaveSimetrica));
            return account;
        }).toList();


        responseMs.setDinBody(all);


        return responseMs;
    }
}
