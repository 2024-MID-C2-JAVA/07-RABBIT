package com.bank.management.config;

import com.bank.management.data.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryPostgresql extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
