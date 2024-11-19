package com.bank.management.gateway;

import com.bank.management.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(String id);
    Optional<Account> save(Account account);
    boolean delete(String id);
    Optional<Account> findByNumber(String accountNumber);
    List<Account> findByCustomerId(String id);
}