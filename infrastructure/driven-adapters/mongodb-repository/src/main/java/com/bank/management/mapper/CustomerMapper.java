package com.bank.management.mapper;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.data.AccountDocument;
import com.bank.management.data.CustomerDocument;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerMapper {

    public static CustomerDocument toDocument(Customer customer) {
        CustomerDocument document = new CustomerDocument();
        document.setId(customer.getId() != null ? customer.getId() : null);
        document.setUsername(customer.getUsername());
        document.setDeleted(customer.isDeleted());
        document.setCreatedAt(customer.getCreatedAt());

        if (customer.getAccounts() != null) {
            List<AccountDocument> accountDocuments = customer.getAccounts().stream()
                    .map(AccountMapper::toDocument)
                    .collect(Collectors.toList());
            document.setAccounts(accountDocuments);

            return document;
        }
        return document;
    }

    public static Customer toDomain(CustomerDocument document) {
        Customer.Builder builder = new Customer.Builder();

        builder.id(document.getId() != null ? document.getId(): null)
                .username(document.getUsername())
                .isDeleted(document.isDeleted())
                .createdAt(document.getCreatedAt());

            if (document.getAccounts() != null) {
                List<Account> accounts = document.getAccounts().stream()
                        .map(AccountMapper::toDomain)
                        .collect(Collectors.toList());
                builder.accounts(accounts);
            }

        return builder.build();
    }
}
