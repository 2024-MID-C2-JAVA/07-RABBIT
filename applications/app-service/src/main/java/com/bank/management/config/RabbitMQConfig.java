package com.bank.management.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name.sucess}")
    private String queueNameSuccess;

    @Value("${rabbitmq.queue.name.error}")
    private String queueNameError;

    @Value("${rabbitmq.routing.key.sucess}")
    private String routingKeySuccess;

    @Value("${rabbitmq.routing.key.error}")
    private String routingKeyError;

    @Bean
    public Queue successQueue() {
        return new Queue(queueNameSuccess, true);
    }

    @Bean
    public Queue errorQueue() {
        return new Queue(queueNameError, true);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding successBinding(Queue successQueue, TopicExchange exchange) {
        return BindingBuilder.bind(successQueue).to(exchange).with(routingKeySuccess);
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, TopicExchange exchange) {
        return BindingBuilder.bind(errorQueue).to(exchange).with(routingKeyError);
    }
}
