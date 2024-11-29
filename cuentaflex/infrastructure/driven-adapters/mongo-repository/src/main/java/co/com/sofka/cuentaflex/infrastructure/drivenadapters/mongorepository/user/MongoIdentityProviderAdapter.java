package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.user;

import co.com.sofka.core.security.authentication.ports.IdentityProviderPort;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.customer.MongoCustomerDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MongoIdentityProviderAdapter implements IdentityProviderPort {
    private final MongoTemplate mongoTemplate;

    public MongoIdentityProviderAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public String getUserIdentity(String username) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userDetails.username").is(username));

        MongoCustomerDocument mongoCustomerDocument = this.mongoTemplate.findOne(query, MongoCustomerDocument.class);

        if (mongoCustomerDocument == null || mongoCustomerDocument.getUserDetails() == null) {
            throw new NoSuchElementException("User not found");
        }

        return mongoCustomerDocument.getId();
    }
}
