package com.bank.management.exception;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException() {
        super("invalid amount");
    }
}