package co.com.sofka.cuentaflex.accountservice.configuration;

import co.com.sofka.cuentaflex.infrastructure.drivenadapters.infobus.BusEncryptionParams;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.infobus.BusQueueParams;
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
    public BusEncryptionParams busEncryptionParams(
            @Value("${bus.encryption.symmetric-key}") String symmetricKey
    ) {
        return new BusEncryptionParams(symmetricKey);
    }

    @Bean
    public BusQueueParams busQueueParams(
            @Value("${bus.queue.exchange}") String exchangeName,
            @Value("${bus.queue.name}") String queueName,
            @Value("${bus.queue.routing-key}") String routingKey
    ) {
        return new BusQueueParams(exchangeName, queueName, routingKey);
    }

    @Bean
    public TopicExchange exchange(BusQueueParams busQueueParams) {
        return new TopicExchange(busQueueParams.getExchangeName());
    }

    @Bean
    public Queue queue(BusQueueParams busQueueParams) {
        return new Queue(busQueueParams.getQueueName(), true);
    }

    @Bean
    public Binding binding(BusQueueParams busQueueParams, Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(busQueueParams.getRoutingKey());
    }
}
