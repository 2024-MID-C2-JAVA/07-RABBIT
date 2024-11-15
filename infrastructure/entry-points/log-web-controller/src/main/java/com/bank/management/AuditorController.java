package com.bank.management;


import com.bank.management.data.RequestMs;
import com.bank.management.data.ResponseMs;
import com.bank.management.enums.DinErrorCode;
import com.bank.management.usecase.logservice.GetLogUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/audit/logs")
public class AuditorController {

    private final GetLogUseCase getLogUseCase;

    public AuditorController(GetLogUseCase getLogUseCase) {
        this.getLogUseCase = getLogUseCase;
    }

    @PostMapping
    public ResponseEntity<ResponseMs<Log>> createAccount(@RequestBody @Valid RequestMs<?> request) {

        try {
            Log log = getLogUseCase.apply();

            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    log,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Ok"
            );
        } catch (Exception e) {
            return ResponseBuilder.buildResponse(
                    request.getDinHeader(),
                    null,
                    DinErrorCode.UNKNOWN_ERROR,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
