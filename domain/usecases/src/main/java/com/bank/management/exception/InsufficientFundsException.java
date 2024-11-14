package com.bank.management.exception;

public class InsufficientFundsException extends RuntimeException {


    public InsufficientFundsException() {
        super("Insufficient funds in the account.");
    }
}
