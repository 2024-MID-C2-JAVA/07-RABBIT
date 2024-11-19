package com.bank.management.exception;

public class AccountCreationException extends RuntimeException  {
    public AccountCreationException(String message) {
        super("An error occurred while creating the account.");
    }
}
