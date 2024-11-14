package co.sofka.usecase.appBank;



import co.sofka.LogEvent;
import co.sofka.gateway.IRabbitBus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveLogTransactionDetailUseCase implements ISaveLogTransactionDetailService {

    private static final Logger logger = LoggerFactory.getLogger(SaveLogTransactionDetailUseCase.class);


    private final IRabbitBus rabbitBus;

    public SaveLogTransactionDetailUseCase(IRabbitBus rabbitBus) {
        this.rabbitBus = rabbitBus;
    }


    public void save(LogEvent logEvent) {
        rabbitBus.send(logEvent);
    }


}
