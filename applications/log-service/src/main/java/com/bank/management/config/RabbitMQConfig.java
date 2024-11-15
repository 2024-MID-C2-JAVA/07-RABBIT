package com.bank.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name.sucess}")
    private String queueNameSuccess;

    @Value("${rabbitmq.queue.name.error}")
    private String queueNameError;

    @Bean
    public String queueNameSuccess() {
        return queueNameSuccess;
    }

    @Bean
    public String queueNameError() {
        return queueNameError;
    }
}
