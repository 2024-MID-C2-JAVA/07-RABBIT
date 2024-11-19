package com.bank.management.usecase.appservice;

import com.bank.management.Customer;
import com.bank.management.gateway.CustomerRepository;

import java.util.List;

public class GetAllCustomersUseCase {

    private final CustomerRepository customerRepository;

    public GetAllCustomersUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> apply() {
        return customerRepository.findAll();
    }
}
