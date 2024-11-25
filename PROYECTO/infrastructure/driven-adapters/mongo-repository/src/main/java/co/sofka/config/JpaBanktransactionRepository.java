package co.sofka.config;


import co.sofka.data.entity.CustomerEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface JpaBanktransactionRepository extends MongoRepository<CustomerEntity, String> {

    @Query("{ 'accounts.number' : ?0 }")
    CustomerEntity findByNumberAccount(String number);
}
