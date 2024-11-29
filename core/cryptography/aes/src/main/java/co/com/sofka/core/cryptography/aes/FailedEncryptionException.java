package co.com.sofka.core.cryptography.aes;

public class FailedEncryptionException extends RuntimeException {
    public FailedEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
