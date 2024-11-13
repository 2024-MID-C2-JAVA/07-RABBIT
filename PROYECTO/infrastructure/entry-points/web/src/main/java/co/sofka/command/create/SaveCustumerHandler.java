package co.sofka.command.create;

import co.sofka.Account;
import co.sofka.Customer;
import co.sofka.command.dto.AccountDTO;
import co.sofka.command.dto.CustomerDTO;
import co.sofka.command.dto.request.CustomerSaveDTO;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.config.EncryptionAndDescryption;
import co.sofka.config.TokenByDinHeaders;
import co.sofka.middleware.CustomerByUsernameExistException;
import co.sofka.middleware.ErrorDecryptingDataException;
import co.sofka.security.configuration.jwt.JwtUtils;
import co.sofka.usecase.IGetAllCustomerService;
import co.sofka.usecase.ISaveCustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class SaveCustumerHandler {

    private static final Logger logger = LoggerFactory.getLogger(SaveCustumerHandler.class);

    private final ISaveCustomerService service;

    private final TokenByDinHeaders utils;

    private EncryptionAndDescryption encryptionAndDescryption;

    private final JwtUtils jwtUtils;

    private final IGetAllCustomerService serviceAll;


    public ResponseMs<CustomerDTO> save(RequestMs<CustomerSaveDTO> request) {

        ResponseMs<CustomerDTO> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(request.getDinHeader());
        DinError error = new DinError();

        Customer customer = new Customer();
        customer.setUsername(request.getDinBody().getUsername());
        customer.setPwd(jwtUtils.encryptionPassword(request.getDinBody().getPwd()));

        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setUsername(customer.getUsername());
        customerDTO.setAccounts(new ArrayList<>());

        serviceAll.getAll().forEach(customer1 -> {
            if(customer1.getUsername().equals(customer.getUsername())){
                throw new CustomerByUsernameExistException("Ya existe un Customer con el usuario proporcionado.",request.getDinHeader(),1006);
            }
        });

        if (request.getDinBody().getAccounts() != null && !request.getDinBody().getAccounts().isEmpty()) {
            List<AccountDTO> accounts = request.getDinBody().getAccounts();
            customerDTO.setAccounts(accounts);

            if (accounts != null && !accounts.isEmpty()) {
                List<Account> accountsAll = new ArrayList<>();
               accounts.forEach(accountDTO -> {
                   Account accountDTO1 = new Account();
                   accountDTO1.setNumber(accountDTO.getNumber());
                   accountDTO1.setAmount(accountDTO.getAmount());
                   accountDTO1.setCreatedAt(LocalDate.now());
                   accountDTO1.setDeleted(false);
                   accountsAll.add(accountDTO1);
               });
                customer.setAccounts(accountsAll);
            }


        }


        service.save(customer);



        responseMs.setDinBody(customerDTO);

        responseMs.setDinError(error);


        return responseMs;
    }
}
