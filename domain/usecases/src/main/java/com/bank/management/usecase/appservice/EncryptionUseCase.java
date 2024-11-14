package com.bank.management.usecase.appservice;

import com.bank.management.gateway.EncryptionGateway;

public class EncryptionUseCase {

    private final EncryptionGateway encryptionGateway;

    public EncryptionUseCase(EncryptionGateway encryptionGateway) {
        this.encryptionGateway = encryptionGateway;
    }

    public String encryptData(String plainText, String base64Key, String base64Iv) {
        return encryptionGateway.encrypt(plainText, base64Key, base64Iv);
    }

    public String decryptData(String cipherText, String base64Key, String base64Iv) {
        return encryptionGateway.decrypt(cipherText, base64Key, base64Iv);
    }
}
