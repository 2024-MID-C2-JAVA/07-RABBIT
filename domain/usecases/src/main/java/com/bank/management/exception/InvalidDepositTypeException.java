package com.bank.management.exception;

public class InvalidDepositTypeException extends RuntimeException {
    public InvalidDepositTypeException(String type) {
        super("Invalid deposit type: " + type);
    }
}