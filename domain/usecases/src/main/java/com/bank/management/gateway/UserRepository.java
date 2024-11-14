package com.bank.management.gateway;

import com.bank.management.User;

import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    Optional <User> findByUsername(String username);
}
