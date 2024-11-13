package co.sofka.middleware;

public class AccountNotExistException extends RuntimeException{

    public AccountNotExistException(String message) {
        super(message);
    }
}
