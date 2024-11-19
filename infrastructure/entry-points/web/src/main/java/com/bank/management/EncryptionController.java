package com.bank.management;

import com.bank.management.data.RequestMs;
import com.bank.management.data.ResponseMs;
import com.bank.management.enums.DinErrorCode;
import com.bank.management.usecase.appservice.EncryptionUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/encryption")
public class EncryptionController {

    private final EncryptionUseCase encryptionUseCase;

    public EncryptionController(EncryptionUseCase encryptionUseCase) {
        this.encryptionUseCase = encryptionUseCase;
    }

    @PostMapping("/encrypt")
    public ResponseEntity<ResponseMs<Map<String, String>>> encryptData(@RequestBody RequestMs<Map<String, String>> requestData) {
        Map<String, String> encryptedData = new HashMap<>();

        try {
            String key = requestData.getDinHeader().getSymmetricKey();
            String iv = requestData.getDinHeader().getInitializationVector();

            for (Map.Entry<String, String> entry : requestData.getDinBody().entrySet()) {
                String dataKey = entry.getKey();
                String value = entry.getValue();
                String encryptedValue = encryptionUseCase.encryptData(value, key, iv);
                encryptedData.put(dataKey, encryptedValue);
            }

            return ResponseBuilder.buildResponse(
                    requestData.getDinHeader(),
                    encryptedData,
                    DinErrorCode.SUCCESS,
                    HttpStatus.OK,
                    "Data encrypted successfully."
            );

        } catch (Exception e) {
            return ResponseBuilder.buildResponse(
                    requestData.getDinHeader(),
                    null,
                    DinErrorCode.ERROR_ENCRYPTING_DATA,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage()
            );
        }
    }
}
