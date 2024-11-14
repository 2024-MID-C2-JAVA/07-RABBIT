package com.bank.management;

import com.bank.management.gateway.UserCreateEventGateway;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class UserEventAdapter implements UserCreateEventGateway{

    private final ApplicationEventPublisher eventPublisher;


    public UserEventAdapter(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }


    @Override
    public void publish(User event) {
        eventPublisher.publishEvent(event);
    }
}
