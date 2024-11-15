package com.bank.management.gateway;

public interface MessageListenerGateway {
    void receiveTransactionSuccess(String message);
    void receiveMessageError(String message);
}
