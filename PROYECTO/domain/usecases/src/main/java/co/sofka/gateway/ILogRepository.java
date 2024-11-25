package co.sofka.gateway;

import co.sofka.LogEvent;

import java.util.List;

public interface ILogRepository {
    void save(LogEvent log);

    List<LogEvent> list();
}
