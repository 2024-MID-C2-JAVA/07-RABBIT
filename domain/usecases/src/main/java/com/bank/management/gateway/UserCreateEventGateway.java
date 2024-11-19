package com.bank.management.gateway;

import com.bank.management.UserEventCreate;

public interface UserCreateEventGateway {
    void publish(UserEventCreate event);
}
