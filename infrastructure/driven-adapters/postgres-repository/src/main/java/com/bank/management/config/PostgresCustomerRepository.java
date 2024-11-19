package com.bank.management.config;

import com.bank.management.data.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostgresCustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByUsername(String username);
    @Query("SELECT c FROM CustomerEntity c JOIN c.accounts a WHERE a.number = :accountNumber")
    CustomerEntity findByAccountNumber(@Param("accountNumber") String accountNumber);
}
