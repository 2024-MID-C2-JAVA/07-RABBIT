package com.bank.management.mapper;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.data.AccountDocument;

public class AccountMapper {

    public static AccountDocument toDocument(Account account) {
        AccountDocument document = new AccountDocument();
        document.setId(account.getId());
        document.setNumber(account.getNumber());
        document.setAmount(account.getAmount());
        document.setCustomerId(account.getCustomer() != null ? account.getCustomer().getId() : null);
        document.setDeleted(account.isDeleted());
        document.setCreatedAt(account.getCreated_at());
        return document;
    }

    public static Account toDomain(AccountDocument document) {
        Customer customer = new Customer.Builder().id(document.getCustomerId()).build();
        return new Account.Builder()
                .id(document.getId())
                .number(document.getNumber())
                .amount(document.getAmount())
                .customer(customer)
                .isDeleted(document.isDeleted())
                .createdAt(document.getCreatedAt())
                .build();
    }
}
