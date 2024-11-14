package co.sofka.gateway;

import co.sofka.LogEvent;

public interface ILogRepository {
    void save(LogEvent log);
}
