package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.config;

import co.com.sofka.shared.infrastructure.entrypoints.din.DinError;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinHeader;
import co.com.sofka.shared.infrastructure.entrypoints.din.DinResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UnkwonErrorsHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity.internalServerError().body(new DinResponse(new DinHeader(), DinError.getUnknownError()));
    }
}
