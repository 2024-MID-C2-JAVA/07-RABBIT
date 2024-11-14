package com.bank.management.usecase.appservice;

import com.bank.management.Customer;
import com.bank.management.exception.CustomerAlreadyExistsException;
import com.bank.management.gateway.CustomerRepository;

import java.util.Optional;

public class CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> apply(Customer customer) {
        Optional<Customer> existingCustomer = customerRepository.findByUsername(customer.getUsername());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException(customer.getUsername());
        }

        return customerRepository.save(customer);
    }
}
