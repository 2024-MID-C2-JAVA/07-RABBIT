package co.sofka.gateway;


import co.sofka.Customer;

import java.util.List;

public interface ICustomerRepository {

    Customer save(Customer customer);
    Customer findById(String id);
    Customer findByUsername(String username);
    List<Customer> getAll();
}
