package co.sofka.usecase.appBank;




import co.sofka.LogEvent;

@FunctionalInterface
public interface ISaveLogTransactionDetailService {
    void save(LogEvent log);


}
