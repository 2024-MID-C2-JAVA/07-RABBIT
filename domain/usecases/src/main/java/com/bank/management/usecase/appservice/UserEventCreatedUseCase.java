package com.bank.management.usecase.appservice;

import com.bank.management.gateway.UserCreateEventGateway;

public class UserEventCreatedUseCase {

    private final UserCreateEventGateway userCreateEventGateway;

    public UserEventCreatedUseCase(UserCreateEventGateway userCreateEventGateway) {
        this.userCreateEventGateway = userCreateEventGateway;
    }

    public void apply(UserEventCreate event) {
        userCreateEventGateway.publish(event);
    }
}
