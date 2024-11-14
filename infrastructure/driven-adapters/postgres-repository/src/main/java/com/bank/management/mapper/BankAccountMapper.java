package com.bank.management.mapper;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.data.AccountEntity;

public class BankAccountMapper {

    public static Account toDomain(AccountEntity entity) {
        if (entity == null) {
            return null;
        }

        Account account = new Account();
        account.setId(entity.getId().toString());
        account.setNumber(entity.getNumber());
        account.setAmount(entity.getAmount());
        account.setCreated_at(entity.getCreatedAt());
        if (entity.getCustomer() != null) {
            Customer customer = new Customer.Builder().id(entity.getCustomer().getId().toString()).build();
            account.setCustomer(customer);
        }

        return account;
    }

    public static AccountEntity toEntity(Account account) {
        if (account == null) {
            return null;
        }
        AccountEntity entity = new AccountEntity();
        entity.setId(account.getId() != null ? Long.valueOf(account.getId()) : null);
        entity.setNumber(account.getNumber());
        entity.setAmount(account.getAmount());
        entity.setCreatedAt(account.getCreated_at());
        entity.setCustomer(CustomerMapper.toEntity(account.getCustomer()));

        return entity;
    }
}
