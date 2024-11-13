package co.sofka.middleware;

public class AccountNotHaveBalanceException extends RuntimeException{

    public AccountNotHaveBalanceException(String message) {
        super(message);
    }
}
