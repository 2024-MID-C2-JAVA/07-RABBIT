package co.com.sofka.core.cryptography.aes;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public final class AESCipher {
    public static String encryptToBase64(String data, String key, String iv) throws FailedEncryptionException {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            throw new FailedEncryptionException("An error occurred while encrypting", e);
        }
    }

    public static String decryptFromBase64(String data, String key, String iv) throws FailedDecryptionException {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedData = Base64.getDecoder().decode(data);
            return new String(cipher.doFinal(encryptedData), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new FailedDecryptionException("An error occurred while decrypting", e);
        }
    }
}
