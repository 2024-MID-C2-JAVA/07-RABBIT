package com.bank.management.gateway;

import com.bank.management.Transaction;

public interface MessageSenderGateway {
    void sendMessage(Transaction trx);
}
