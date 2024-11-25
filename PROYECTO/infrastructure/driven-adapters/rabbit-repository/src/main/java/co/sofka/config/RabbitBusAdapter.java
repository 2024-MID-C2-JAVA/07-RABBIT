package co.sofka.config;


import co.sofka.LogEvent;
import co.sofka.gateway.IRabbitBus;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RabbitBusAdapter implements IRabbitBus {

    @Value("${general.config.rabbitmq.exchange}")
    private String exchange;

    @Value("${general.config.rabbitmq.queue}")
    private String queue;

    @Value("${general.config.rabbitmq.routingkey}")
    private String routingkey;

    private final RabbitTemplate rabbitTemplate;

    public RabbitBusAdapter(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(LogEvent logEvent) {
        rabbitTemplate.convertAndSend(exchange, routingkey, logEvent);
    }
}
