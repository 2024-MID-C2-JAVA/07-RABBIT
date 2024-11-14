package co.sofka.config;

import co.sofka.data.entity.AccountEntity;
import co.sofka.data.entity.LogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLogRepository extends JpaRepository<LogEntity, Long> {



}
