package com.bank.management.gateway;

import com.bank.management.Transaction;

public interface MessageSenderGateway {
    void sendMessageError(String message);
    void sendTransactionSuccess(Transaction trx);}
