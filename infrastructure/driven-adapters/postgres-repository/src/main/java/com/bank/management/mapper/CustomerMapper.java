package com.bank.management.mapper;

import com.bank.management.Customer;
import com.bank.management.data.CustomerEntity;

import java.util.stream.Collectors;

public class CustomerMapper {

    public static Customer toDomain(CustomerEntity entity) {
        if (entity == null) {
            return null;
        }
        Customer customer = new Customer.Builder()
                .id(entity.getId().toString())
                .username(entity.getUsername())
                .build();

        if (entity.getAccounts() != null) {
            customer.setAccounts(entity.getAccounts().stream()
                    .map(BankAccountMapper::toDomain)
                    .collect(Collectors.toList()));
        }
        return customer;
    }

    public static CustomerEntity toEntity(Customer customer) {
        if (customer == null) {
            return null;
        }
        CustomerEntity entity = new CustomerEntity();
        entity.setId(customer.getId() != null ? Long.valueOf(customer.getId()) : null);
        entity.setUsername(customer.getUsername());
        entity.setCreatedAt(customer.getCreatedAt());

        if (customer.getAccounts() != null) {
            entity.setAccounts(customer.getAccounts().stream()
                    .map(BankAccountMapper::toEntity)
                    .collect(Collectors.toList()));
        }
        return entity;
    }
}
