package co.com.sofka.core.cryptography.aes;

public class FailedDecryptionException extends RuntimeException {
    public FailedDecryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
