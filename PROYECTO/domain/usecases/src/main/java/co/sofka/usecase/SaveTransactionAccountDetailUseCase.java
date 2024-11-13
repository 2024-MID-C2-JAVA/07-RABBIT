package co.sofka.usecase;



import co.sofka.Transaction;
import co.sofka.TransactionAccountDetail;
import co.sofka.gateway.ITransactionAccountDetailRepository;
import co.sofka.gateway.ITransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveTransactionAccountDetailUseCase implements ISaveTransactionAccountDetailService {

    private static final Logger logger = LoggerFactory.getLogger(SaveTransactionAccountDetailUseCase.class);

    private final ITransactionAccountDetailRepository repository;

    public SaveTransactionAccountDetailUseCase(ITransactionAccountDetailRepository repository) {
        this.repository = repository;
    }


    public TransactionAccountDetail save(TransactionAccountDetail transaction) {

        return repository.save(transaction);
    }


}
