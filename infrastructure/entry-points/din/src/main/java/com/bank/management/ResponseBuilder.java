package com.bank.management;

import com.bank.management.data.DinError;
import com.bank.management.data.DinHeader;
import com.bank.management.data.ResponseMs;
import com.bank.management.enums.DinErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


public class ResponseBuilder {

    private ResponseBuilder() {
    }

    public static <T> ResponseEntity<ResponseMs<T>> buildResponse(
            DinHeader requestHeader, T body, DinErrorCode dinErrorCode, HttpStatus status, String detailMessage) {

        DinError dinError = new DinError(dinErrorCode.getType(), "api-bank-management-instance-1",
                dinErrorCode.getCode(),
                dinErrorCode.getErrorCodeProvider(),
                dinErrorCode.getMessage(),
                detailMessage);

        ResponseMs<T> responseMs = new ResponseMs<>(requestHeader, body, dinError);
        return ResponseEntity.status(status).body(responseMs);
    }
}
