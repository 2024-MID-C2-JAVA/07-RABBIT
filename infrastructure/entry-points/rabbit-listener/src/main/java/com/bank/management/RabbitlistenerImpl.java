package com.bank.management;

import com.bank.management.gateway.MessageListenerGateway;
import com.bank.management.usecase.logservice.SaveLogUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitlistenerImpl implements MessageListenerGateway {

    private final SaveLogUseCase saveLog;

    public RabbitlistenerImpl(SaveLogUseCase saveLog) {
        this.saveLog = saveLog;
    }

    @Override
    @RabbitListener(queues = "logs")
    public void receiveMessage(String message) {
        saveLog.apply(message);
    }
}
