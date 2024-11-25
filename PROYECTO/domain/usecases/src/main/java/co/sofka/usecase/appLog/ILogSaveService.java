package co.sofka.usecase.appLog;


import co.sofka.LogEvent;

@FunctionalInterface
public interface ILogSaveService {
    void save(LogEvent message);
}
