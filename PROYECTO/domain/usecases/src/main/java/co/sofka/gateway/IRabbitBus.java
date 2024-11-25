package co.sofka.gateway;

import co.sofka.LogEvent;

public interface IRabbitBus {
    void send(LogEvent logEvent);
}
