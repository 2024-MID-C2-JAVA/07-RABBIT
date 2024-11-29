package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountTransactionRepository  extends JpaRepository<JpaAccountTransactionEntity, Integer> {
}
