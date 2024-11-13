package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.user;

import co.com.sofka.core.security.authentication.ports.IdentityProviderPort;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerEntity;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PostgresIdentityProviderAdapter implements IdentityProviderPort {
    private final JpaCustomerRepository jpaCustomerRepository;

    public PostgresIdentityProviderAdapter(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public String getUserIdentity(String username) {
        JpaCustomerEntity jpaCustomerEntity = this.jpaCustomerRepository
                .findByUserDetails_Username(username)
                .orElseThrow(() -> new NoSuchElementException("User not found"));

        return String.valueOf(jpaCustomerEntity.getId());
    }
}
