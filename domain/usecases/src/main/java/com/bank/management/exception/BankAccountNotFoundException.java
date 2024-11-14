package com.bank.management.exception;

public class BankAccountNotFoundException extends RuntimeException {
    public BankAccountNotFoundException() {
        super("Account not found");
    }
}