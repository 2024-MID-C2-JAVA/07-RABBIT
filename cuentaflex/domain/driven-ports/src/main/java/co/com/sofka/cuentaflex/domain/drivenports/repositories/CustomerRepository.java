package co.com.sofka.cuentaflex.domain.drivenports.repositories;

import co.com.sofka.cuentaflex.domain.models.Customer;

public interface CustomerRepository {
    boolean existsCustomer(String customerId);
}
