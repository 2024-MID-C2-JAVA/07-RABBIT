package co.sofka.middleware;


import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.*;


@ControllerAdvice
@ComponentScan(basePackages = "co.com.sofka")
public class ServiceErrorHandler {

    private static final Logger logger_ = LoggerFactory.getLogger(ServiceErrorHandler.class);



    @ExceptionHandler(value = {NoSuchElementException.class})
    public ResponseEntity<Object> noSuchElementException(NoSuchElementException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccountNotExistException.class)
    public final ResponseEntity<Object> handleAccountNotExistException(AccountNotExistException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(AccountNotHaveBalanceException.class)
    public final ResponseEntity<Object> handleAccountNotHaveBalanceException(AccountNotHaveBalanceException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TypeOfBuysNotExistException.class)
    public final ResponseEntity<Object> handleTypeOfBuysNotExistException(TypeOfBuysNotExistException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleFilesNotFoundException(NotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);     }

    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<Object> handleBadRequestException(BadRequestException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);     }

    @ExceptionHandler(NoResourceFoundException.class)
    public final ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);     }

    @ExceptionHandler(value = {ClassNotFoundException.class})
    public ResponseEntity<Object> classNotFoundException(ClassNotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.NOT_FOUND.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.NOT_FOUND);         }

    @ExceptionHandler(value = {IllegalStateException.class})
    public ResponseEntity<Object> handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);       }



    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleException(Exception ex, WebRequest request) {
        logger_.error("Error ExceptionHandler.: {}", ex);
        var message = "Error desconocido. Por favor, contactarse con el Administrador del Sistema";
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), message);
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        List<String> errors = new ArrayList<>();
        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));
        Map<String, List<String>> result = new HashMap<>();
        result.put("errors", errors);
        ErrorMessage errorMessage = new ErrorMessage(new Date(), HttpStatus.BAD_REQUEST.toString(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.BAD_REQUEST);          }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<?> handleBindException(BindException bindException) {

        Map<String, String> errorMap = new HashMap<>();

        bindException.getAllErrors().
                forEach(objectError -> {
                    errorMap.put(
                            ((FieldError) objectError).getField(),
                            objectError.getDefaultMessage());
                });

        return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
    }


}

