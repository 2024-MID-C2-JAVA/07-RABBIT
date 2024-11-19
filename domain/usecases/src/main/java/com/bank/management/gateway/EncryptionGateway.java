package com.bank.management.gateway;

public interface EncryptionGateway {
    String encrypt(String plainText, String base64Key, String base64Iv);
    String decrypt(String cipherText, String base64Key, String base64Iv);
}
