package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.user;

import co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.customer.MongoCustomerDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MongoUserDetailsService implements UserDetailsService {
    private final MongoTemplate mongoTemplate;

    public MongoUserDetailsService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = new Query();
        query.addCriteria(Criteria.where("userDetails.username").is(username));

        MongoCustomerDocument mongoCustomerDocument = this.mongoTemplate.findOne(query, MongoCustomerDocument.class);

        if (mongoCustomerDocument == null || mongoCustomerDocument.getUserDetails() == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return mongoCustomerDocument.getUserDetails();
    }
}
