package com.bank.management.gateway;

import com.bank.management.User;
import com.bank.management.usecase.appservice.UserEventCreate;

public interface UserCreateEventGateway {
    void publish(UserEventCreate event);
}
