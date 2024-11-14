package com.bank.management.usecase.appservice;

import com.bank.management.Account;
import com.bank.management.gateway.AccountRepository;

import java.util.List;

public class GetAccountsByCustomerUseCase {

    private final AccountRepository bankAccountRepository;

    public GetAccountsByCustomerUseCase(AccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<Account> apply(String id) {
        return bankAccountRepository.findByCustomerId(id);
    }

}