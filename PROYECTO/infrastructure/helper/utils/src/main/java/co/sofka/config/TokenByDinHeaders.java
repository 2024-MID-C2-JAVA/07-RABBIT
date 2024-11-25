package co.sofka.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Component
public class TokenByDinHeaders {

    @Value("${clave.secret}")
    private String secretKey;

    public String encode(String data) {
        try {
            String ALGORITHM = "AES";
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);
            SecretKeySpec keySpec = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            throw new RuntimeException("Error encrypting data", e);
        }

    }


    public String decode(String encryptedData) {
        try {
            String ALGORITHM = "AES";
            // Decode the secret key
            byte[] decodedKey = Base64.getDecoder().decode(secretKey);

            // Create a new SecretKeySpec for the specified key data and algorithm name
            SecretKeySpec keySpec = new SecretKeySpec(decodedKey, 0, decodedKey.length, ALGORITHM);

            // Create a new Cipher for the specified algorithm
            Cipher cipher = Cipher.getInstance(ALGORITHM);

            // Initialize the Cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, keySpec);

            // Decrypt the data
            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData));

            // Convert the decrypted bytes to a string
            String decryptedString = new String(decryptedBytes);

            return decryptedString;

        } catch (Exception e) {
            throw new RuntimeException("Error decrypting data", e);
        }


    }
}
