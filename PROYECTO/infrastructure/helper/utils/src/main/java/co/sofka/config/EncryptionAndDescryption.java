package co.sofka.config;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class EncryptionAndDescryption {



    private String ALGORITHM = "AES";
    private String CRYPT_ALGORITHM = "AES/CBC/PKCS5PADDING";
//    private final String VECTOR_INICIALIZACION = "1234567890123888";
//    private final String LLAVE_SIMETRICA = "UjXn2r5u8x/A?9D(";


    public String encriptAes(String datoAEncriptar,String VECTOR_INICIALIZACION,String LLAVE_SIMETRICA){
        try{
            IvParameterSpec iv = new IvParameterSpec(VECTOR_INICIALIZACION.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(LLAVE_SIMETRICA.getBytes(StandardCharsets.UTF_8), ALGORITHM);

            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, iv);

            byte[] encrypted = cipher.doFinal(datoAEncriptar.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error crypting";
    }


    public String decryptAes(String datoADesencriptar,String VECTOR_INICIALIZACION,String LLAVE_SIMETRICA){
        try{
            IvParameterSpec iv = new IvParameterSpec(VECTOR_INICIALIZACION.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec secretKeySpec = new SecretKeySpec(LLAVE_SIMETRICA.getBytes(StandardCharsets.UTF_8), ALGORITHM);

            Cipher cipher = Cipher.getInstance(CRYPT_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, iv);

            byte[] original = cipher.doFinal(Base64.getDecoder().decode(datoADesencriptar));
            return new String(original);

        }catch (Exception e){
            e.printStackTrace();
        }
        return "Error crypting";
    }

}
