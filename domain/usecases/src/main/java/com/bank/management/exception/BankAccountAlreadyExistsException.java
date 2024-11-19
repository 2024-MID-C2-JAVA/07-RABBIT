package com.bank.management.exception;

public class BankAccountAlreadyExistsException extends RuntimeException {

    public BankAccountAlreadyExistsException() {

        super("Account already exists.");
    }

}
