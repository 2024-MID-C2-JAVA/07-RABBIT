package co.sofka.middleware;


import co.sofka.Account;
import co.sofka.command.dto.response.DinError;
import co.sofka.command.dto.response.ResponseMs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.*;


@ControllerAdvice
@ComponentScan(basePackages = "co.com.sofka")
public class ServiceAPIErrorHandler {

    private static final Logger logger_ = LoggerFactory.getLogger(ServiceAPIErrorHandler.class);



    @ExceptionHandler(value = {ErrorDecryptingDataException.class})
    public ResponseEntity<Object> ErrorDecryptingDataException(ErrorDecryptingDataException ex, WebRequest request) {
        ResponseMs<Account> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(ex.getDinHeader());
        DinError error = new DinError();

            error.setMensaje(ex.getMessage());
            error.setCodigo(String.valueOf(ex.getCode()));
            error.setFecha(LocalDateTime.now().toString());
            error.setTipo("ERROR");
            error.setOrigen(this.getClass().getName());
            error.setDetalle(ex.getLocalizedMessage());
            responseMs.setDinError(error);

        return new ResponseEntity<>(responseMs, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {CustomerByUsernameExistException.class})
    public ResponseEntity<Object> CustomerByUsernameExistException(CustomerByUsernameExistException ex, WebRequest request) {
        ResponseMs<Account> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(ex.getDinHeader());
        DinError error = new DinError();

        error.setMensaje(ex.getMessage());
        error.setCodigo(String.valueOf(ex.getCode()));
        error.setFecha(LocalDateTime.now().toString());
        error.setTipo("ERROR");
        error.setOrigen(this.getClass().getName());
        error.setDetalle(ex.getLocalizedMessage());
        responseMs.setDinError(error);

        return new ResponseEntity<>(responseMs, new HttpHeaders(), HttpStatus.OK);
    }


    @ExceptionHandler(value = {CustomerNotExistException.class})
    public ResponseEntity<Object> CustomerNotExistException(CustomerNotExistException ex, WebRequest request) {
        ResponseMs<Account> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(ex.getDinHeader());
        DinError error = new DinError();

        error.setMensaje(ex.getMessage());
        error.setCodigo(String.valueOf(ex.getCode()));
        error.setFecha(LocalDateTime.now().toString());
        error.setTipo("ERROR");
        error.setOrigen(this.getClass().getName());
        error.setDetalle(ex.getLocalizedMessage());
        responseMs.setDinError(error);

        return new ResponseEntity<>(responseMs, new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(AccountNotExistException.class)
    public final ResponseEntity<Object> handleAccountNotExistException(AccountNotExistException ex, WebRequest request) {

        ResponseMs<Account> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(ex.getDinHeader());
        DinError error = new DinError();

        error.setMensaje(ex.getMessage());
        error.setCodigo(String.valueOf(ex.getCode()));
        error.setFecha(LocalDateTime.now().toString());
        error.setTipo("ERROR");
        error.setOrigen(this.getClass().getName());
        error.setDetalle(ex.getLocalizedMessage());
        responseMs.setDinError(error);

        return new ResponseEntity<>(responseMs, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AccountNotHaveBalanceException.class)
    public final ResponseEntity<Object> handleAccountNotHaveBalanceException(AccountNotHaveBalanceException ex, WebRequest request) {
        ResponseMs<Account> responseMs = new ResponseMs<>();
        responseMs.setDinHeader(ex.getDinHeader());
        DinError error = new DinError();

        error.setMensaje(ex.getMessage());
        error.setCodigo(String.valueOf(ex.getCode()));
        error.setFecha(LocalDateTime.now().toString());
        error.setTipo("ERROR");
        error.setOrigen(this.getClass().getName());
        error.setDetalle(ex.getLocalizedMessage());
        responseMs.setDinError(error);

        return new ResponseEntity<>(responseMs, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }



}

