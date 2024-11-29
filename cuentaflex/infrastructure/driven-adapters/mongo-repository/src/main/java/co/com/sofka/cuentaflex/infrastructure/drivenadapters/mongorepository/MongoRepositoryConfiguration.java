package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository")
@EnableMongoRepositories(basePackages = "co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository")
public class MongoRepositoryConfiguration {
}