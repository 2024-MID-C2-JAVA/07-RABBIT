package co.com.sofka.cuentaflex.logservice.configuration;

import co.com.sofka.cuentaflex.infrastructure.entrypoints.mqlistener.MqListenerEncryptionParams;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
    @Bean
    public MqListenerEncryptionParams mqListenerEncryptionParams(
            @Value("${listener.encryption.symmetric-key}") String symmetricKey
    ) {
        return new MqListenerEncryptionParams(symmetricKey);
    }

    @Bean
    public TopicExchange exchange(@Value("${listener.queue.exchange}") String exchangeName) {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Queue queue(@Value("${listener.queue.name}") String queueName) {
        return new Queue(queueName, true);
    }

    @Bean
    public Binding binding(@Value("${listener.queue.routing-key}") String routingKey, Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }
}
