package com.bank.management;

import com.bank.management.config.UserRepositoryPostgresql;
import com.bank.management.data.UserEntity;
import com.bank.management.gateway.UserRepository;
import com.bank.management.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final UserRepositoryPostgresql userRepositoryPostgresql;
    private final PasswordEncoder passwordEncoder;

    public UserRepositoryAdapter(UserRepositoryPostgresql userRepositoryPostgresql, PasswordEncoder passwordEncoder) {
        this.userRepositoryPostgresql = userRepositoryPostgresql;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveUser(User user) {
        UserEntity userEntity =  UserMapper.toEntity(user);
        user.setPassword(userEntity.getPassword());
        return UserMapper.toDomain(userRepositoryPostgresql.save(userEntity));
    }

    @Override
    public Optional<User> findByUsername(String username) {
       Optional<UserEntity> userFound = userRepositoryPostgresql.findByUsername(username);
        return userFound.map(UserMapper::toDomain);
    }
}
