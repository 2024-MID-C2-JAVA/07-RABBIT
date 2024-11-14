package com.bank.management.gateway;

import com.bank.management.User;

public interface UserCreateEventGateway {
    void publish(User event);
}
