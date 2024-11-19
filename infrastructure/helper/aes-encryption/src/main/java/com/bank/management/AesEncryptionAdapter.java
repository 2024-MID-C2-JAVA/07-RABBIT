package com.bank.management;

import com.bank.management.gateway.EncryptionGateway;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class AesEncryptionAdapter implements EncryptionGateway {

    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";

    @Override
    public String encrypt(String plainText, String base64Key, String base64Iv) {
        try {
            byte[] key = Base64.getDecoder().decode(base64Key);
            byte[] iv = Base64.getDecoder().decode(base64Iv);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }
    }

    @Override
    public String decrypt(String cipherText, String base64Key, String base64Iv) {
        try {
            byte[] key = Base64.getDecoder().decode(base64Key);
            byte[] iv = Base64.getDecoder().decode(base64Iv);
            byte[] decodedCipherText = Base64.getDecoder().decode(cipherText);

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
            byte[] decryptedBytes = cipher.doFinal(decodedCipherText);
            return new String(decryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }
    }
}
