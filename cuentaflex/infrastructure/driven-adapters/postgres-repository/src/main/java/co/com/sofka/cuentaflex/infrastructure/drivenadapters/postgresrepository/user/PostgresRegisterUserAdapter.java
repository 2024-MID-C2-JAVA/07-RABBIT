package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.user;

import co.com.sofka.core.security.authentication.ports.RegisterUserPort;
import co.com.sofka.core.security.authentication.ports.RegisterUserRequest;
import co.com.sofka.core.security.authentication.ports.RegisterUserResponse;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerEntity;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PostgresRegisterUserAdapter extends RegisterUserPort {
    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaCustomerUserDetailsRepository jpaCustomerUserDetailsRepository;

    public PostgresRegisterUserAdapter(
            PasswordEncoder passwordEncoder,
            UserDetailsService userDetailsService,
            JpaCustomerRepository jpaCustomerRepository,
            JpaCustomerUserDetailsRepository jpaCustomerUserDetailsRepository
    ) {
        super(passwordEncoder, userDetailsService);

        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaCustomerUserDetailsRepository = jpaCustomerUserDetailsRepository;
    }

    @Override
    @Transactional
    protected RegisterUserResponse handleRegisterUser(RegisterUserRequest request) {
        JpaCustomerEntity jpaCustomerEntity = new JpaCustomerEntity();
        jpaCustomerEntity.setFirstName(request.getFirstName());
        jpaCustomerEntity.setLastName(request.getLastName());
        jpaCustomerEntity.setIdentification(request.getEncryptedIdentification());
        jpaCustomerEntity.setDeleted(false);
        jpaCustomerEntity.setCreatedAt(LocalDateTime.now());

        JpaCustomerEntity savedCustomer = this.jpaCustomerRepository.save(jpaCustomerEntity);

        JpaCustomerUserDetailsEntity jpaCustomerUserDetailsEntity = new JpaCustomerUserDetailsEntity();
        jpaCustomerUserDetailsEntity.setCustomer(savedCustomer);
        jpaCustomerUserDetailsEntity.setUsername(request.getUsername());
        jpaCustomerUserDetailsEntity.setPassword(request.getEncryptedPassword());
        this.jpaCustomerUserDetailsRepository.save(jpaCustomerUserDetailsEntity);

        return new RegisterUserResponse(String.valueOf(savedCustomer.getId()));
    }
}
