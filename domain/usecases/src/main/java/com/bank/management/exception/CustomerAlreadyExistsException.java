package com.bank.management.exception;


public class CustomerAlreadyExistsException extends RuntimeException {
    public CustomerAlreadyExistsException(String username) {
        super("Customer with username " + username + " already exists.");
    }
}