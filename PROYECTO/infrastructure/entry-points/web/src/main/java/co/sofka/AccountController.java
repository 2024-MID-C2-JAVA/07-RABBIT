package co.sofka;


import co.sofka.command.dto.request.AccountFindRequest;
import co.sofka.command.dto.request.RequestMs;
import co.sofka.command.dto.response.ResponseMs;
import co.sofka.command.query.FindAccountByNumberHandler;
import co.sofka.command.query.ListAllAccountHandler;
import co.sofka.data.ResponseAPI;
import co.sofka.usecase.IGetAllAccountService;
import co.sofka.usecase.IGetAllCustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    private final ListAllAccountHandler handler;

    private final FindAccountByNumberHandler findAccountByNumberHandler;


    @PostMapping("/all")
    public ResponseEntity<ResponseMs<List<Account>>> getAll(@RequestBody RequestMs<Void> request) {
        logger.info("Buscando todos los Account");
        return new ResponseEntity<>( handler.getAll(request), HttpStatus.OK);
    }


    @PostMapping("/byNumber")
    public ResponseEntity<ResponseMs<Account>> getByNumber(@RequestBody RequestMs<AccountFindRequest> request) {
        logger.info("Buscando todos los Account por numero");
        return new ResponseEntity<>( findAccountByNumberHandler.findByNumber(request), HttpStatus.OK);
    }



}
