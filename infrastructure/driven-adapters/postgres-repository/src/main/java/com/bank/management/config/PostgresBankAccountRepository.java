package com.bank.management.config;

import com.bank.management.data.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostgresBankAccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCustomerId(Long customerId);
    Optional<AccountEntity> findByNumber(String accountNumber);
}
