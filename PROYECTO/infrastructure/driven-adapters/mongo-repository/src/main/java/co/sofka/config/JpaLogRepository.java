package co.sofka.config;


import co.sofka.data.entity.CustomerEntity;
import co.sofka.data.entity.LogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface JpaLogRepository extends MongoRepository<LogEntity, String> {

}
