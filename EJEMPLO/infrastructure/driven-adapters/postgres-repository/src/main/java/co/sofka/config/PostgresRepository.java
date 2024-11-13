package co.sofka.config;

import co.sofka.data.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostgresRepository extends JpaRepository<AccountEntity, Integer> {
}
