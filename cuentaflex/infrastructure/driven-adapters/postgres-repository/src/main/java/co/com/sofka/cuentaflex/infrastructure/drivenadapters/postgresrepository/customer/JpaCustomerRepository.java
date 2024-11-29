package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<JpaCustomerEntity, Integer> {
    Optional<JpaCustomerEntity> findByUserDetails_Username(String username);
}
