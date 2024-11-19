package com.bank.management.usecase.appservice;

import com.bank.management.Customer;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.gateway.CustomerRepository;

import java.util.Optional;

public class DeleteCustomerUseCase{

    private final CustomerRepository customerRepository;

    public DeleteCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public boolean apply(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(id);
        }
        Customer customer = customerOptional.get();
        customerRepository.delete(customer);
        return true;
    }
}
