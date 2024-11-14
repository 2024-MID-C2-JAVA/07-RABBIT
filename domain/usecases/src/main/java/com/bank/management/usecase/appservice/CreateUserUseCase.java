package com.bank.management.usecase.appservice;

import com.bank.management.User;
import com.bank.management.exception.CustomerAlreadyExistsException;
import com.bank.management.gateway.UserCreateEventGateway;
import com.bank.management.gateway.UserRepository;

import java.util.Optional;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final UserCreateEventGateway userCreateEventGateway;

    public CreateUserUseCase(UserRepository userRepository, UserCreateEventGateway userCreateEventGateway) {
        this.userRepository = userRepository;
        this.userCreateEventGateway = userCreateEventGateway;
    }

    public User apply(User user) {
        Optional<User> userFound = userRepository.findByUsername(user.getUsername());
        if (userFound.isPresent()) {
            throw new CustomerAlreadyExistsException(user.getUsername());
        }
        User userCreated = new User(user.getUsername(), user.getPassword());
        userCreateEventGateway.publish(userCreated);
        return userRepository.saveUser(userCreated);
    }
}
