package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.user;

import co.com.sofka.core.security.authentication.ports.RegisterUserPort;
import co.com.sofka.core.security.authentication.ports.RegisterUserRequest;
import co.com.sofka.core.security.authentication.ports.RegisterUserResponse;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.customer.MongoCustomerDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class MongoRegisterUserAdapter extends RegisterUserPort {
    private final MongoTemplate mongoTemplate;

    public MongoRegisterUserAdapter(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, MongoTemplate mongoTemplate) {
        super(passwordEncoder, userDetailsService);
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    @Transactional
    protected RegisterUserResponse handleRegisterUser(RegisterUserRequest request) {
        MongoCustomerUserDetailsDocument userDetails = new MongoCustomerUserDetailsDocument(
                request.getUsername(),
                request.getEncryptedPassword()
        );

        MongoCustomerDocument customer = new MongoCustomerDocument(
                null,
                request.getFirstName(),
                request.getLastName(),
                request.getEncryptedIdentification(),
                LocalDateTime.now(),
                false,
                null,
                userDetails
        );

        MongoCustomerDocument insertedCustomer = this.mongoTemplate.insert(customer);

        return new RegisterUserResponse(insertedCustomer.getId());
    }
}
