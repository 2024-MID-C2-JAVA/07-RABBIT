package co.com.sofka.cuentaflex.infrastructure.drivenadapters.infobus;

public final class BusQueueParams {
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;

    public BusQueueParams(String exchangeName, String queueName, String routingKey) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getQueueName() {
        return queueName;
    }

    public String getRoutingKey() {
        return routingKey;
    }
}
