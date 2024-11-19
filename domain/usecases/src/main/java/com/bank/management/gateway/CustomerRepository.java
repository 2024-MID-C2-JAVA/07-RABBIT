package com.bank.management.gateway;

import com.bank.management.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    List<Customer> findAll();
    Optional<Customer> findById(String id);
    Optional<Customer> save(Customer customer);
    Optional<Customer> findByUsername(String username);
    boolean delete(Customer customer);
    Optional<Customer> findByNumber(String accountNumber);
}
