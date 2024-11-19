package com.bank.management.gateway;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.Transaction;

import java.util.Optional;

public interface TransactionRepository {
    Optional<Transaction> save(Transaction trx, Account account, Customer customer, String role);
}
