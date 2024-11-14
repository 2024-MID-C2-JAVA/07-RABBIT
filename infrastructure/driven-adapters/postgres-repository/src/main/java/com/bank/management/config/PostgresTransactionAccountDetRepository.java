package com.bank.management.config;

import com.bank.management.data.TransactionAccountDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresTransactionAccountDetRepository extends JpaRepository<TransactionAccountDetailEntity, Long> {
}
