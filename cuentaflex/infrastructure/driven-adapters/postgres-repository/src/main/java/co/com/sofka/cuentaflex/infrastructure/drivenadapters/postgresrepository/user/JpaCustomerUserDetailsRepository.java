package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCustomerUserDetailsRepository extends JpaRepository<JpaCustomerUserDetailsEntity, Integer> {
    Optional<JpaCustomerUserDetailsEntity> findByUsername(String username);
}
