package com.bank.management.exception;

public class AccountNotBelongsToCustomerException extends RuntimeException {
    public AccountNotBelongsToCustomerException() {
        super("Account does not belong to the specified customer.");
    }
}