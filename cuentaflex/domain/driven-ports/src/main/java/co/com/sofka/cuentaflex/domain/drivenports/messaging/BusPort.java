package co.com.sofka.cuentaflex.domain.drivenports.messaging;

public interface BusPort {
    void send(Message message);
}
