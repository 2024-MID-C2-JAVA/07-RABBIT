package com.bank.management.usecase.appservice;

import com.bank.management.Account;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.gateway.AccountRepository;

import java.util.Optional;


public class GetBankAccountUseCase {

    private final AccountRepository bankAccountRepository;

    public GetBankAccountUseCase(AccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public Account apply(String id) {
        Optional<Account> accountOptional = bankAccountRepository.findById(id);
        if (accountOptional.isEmpty()) {
            throw new BankAccountNotFoundException();
        }
        return accountOptional.get();
    }

}
