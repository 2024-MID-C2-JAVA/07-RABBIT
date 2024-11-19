package com.bank.management;


import com.bank.management.gateway.CustomerRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CustomerEventListener {

    private final CustomerRepository customerRepository;

    public CustomerEventListener(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @EventListener
    public void handleUserCreatedEvent(User event) {
        Customer customer = new Customer.Builder().id(event.getId()).username(event.getUsername()).build();
        customerRepository.save(customer);
    }
}
