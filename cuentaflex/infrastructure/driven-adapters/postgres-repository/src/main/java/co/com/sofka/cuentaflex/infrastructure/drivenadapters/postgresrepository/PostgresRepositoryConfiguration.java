package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository")
@EnableJpaRepositories(basePackages = "co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository")
@EntityScan(basePackages = "co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository")
public class PostgresRepositoryConfiguration {
}