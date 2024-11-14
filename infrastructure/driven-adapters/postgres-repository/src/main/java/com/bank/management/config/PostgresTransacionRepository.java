package com.bank.management.config;

import com.bank.management.data.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresTransacionRepository extends JpaRepository<TransactionEntity, Long> {
}
