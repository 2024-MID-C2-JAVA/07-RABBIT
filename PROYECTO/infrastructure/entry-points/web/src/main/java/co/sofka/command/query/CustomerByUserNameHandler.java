package co.sofka.command.query;

import co.sofka.Customer;
import co.sofka.command.dto.AccountDTO;
import co.sofka.command.dto.CurstomerByUsername;
import co.sofka.command.dto.CustomerDTO;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.EncryptionAndDescryption;
import co.sofka.config.TokenByDinHeaders;
import co.sofka.middleware.CustomerNotExistException;
import co.sofka.middleware.ErrorDecryptingDataException;
import co.sofka.usecase.appBank.IGetAllCustomerService;
import co.sofka.usecase.appBank.IGetCustomerByUserNameService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class CustomerByUserNameHandler {

    private static final Logger logger = LoggerFactory.getLogger(CustomerByUserNameHandler.class);

    IGetCustomerByUserNameService getCustomerByUserNameService;

    private final TokenByDinHeaders utils;

    private EncryptionAndDescryption encryptionAndDescryption;

    public ResponseMs<CustomerDTO> apply(RequestMs<CurstomerByUsername> request) {

        ResponseMs<CustomerDTO> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();


        Customer byUsername = getCustomerByUserNameService.findByUsername(request.getDinBody().getUsername());

        if (byUsername == null) {
            throw new CustomerNotExistException("Customer no definido para estos parametros.", request.getDinHeader(), 1004);
        }

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername(byUsername.getUsername());
        customerDTO.setRol(byUsername.getRol());


        if (byUsername.getAccounts() != null && !byUsername.getAccounts().isEmpty()) {
            List<AccountDTO> list = byUsername.getAccounts().stream().map(account1 -> {
                AccountDTO accountDTO = new AccountDTO();
                logger.info("Account number: " + account1.getNumber());

                String LlaveSimetrica = "";
                try {
                    LlaveSimetrica = utils.decode(request.getDinHeader().getLlaveSimetrica());
                } catch (Exception e) {
                    throw new ErrorDecryptingDataException("Error al desencriptar la LlaveSimetrica.", request.getDinHeader(), 1001);
                }

                String vectorInicializacion = "";
                try {
                    vectorInicializacion = utils.decode(request.getDinHeader().getVectorInicializacion());
                } catch (Exception e) {
                    throw new ErrorDecryptingDataException("Error al desencriptar la vectorInicializacion.", request.getDinHeader(), 1001);
                }

                accountDTO.setNumber(encryptionAndDescryption.encriptAes(account1.getNumber(), vectorInicializacion, LlaveSimetrica));
                accountDTO.setAmount(account1.getAmount());
                return accountDTO;
            }).toList();
            customerDTO.setAccounts(list);
        }

            responseMs.setDinBody(customerDTO);

            responseMs.setDinError(error);


            return responseMs;
        }
    }

