package co.sofka.usecase.appLog;



import co.sofka.LogEvent;
import co.sofka.Transaction;
import co.sofka.gateway.ILogRepository;
import co.sofka.gateway.ITransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveLogUseCase implements ILogSaveService {

    private static final Logger logger = LoggerFactory.getLogger(SaveLogUseCase.class);

    private final ILogRepository repository;

    public SaveLogUseCase(ILogRepository repository) {
        this.repository = repository;
    }


    public void save(LogEvent transaction) {
        repository.save(transaction);
    }


}
