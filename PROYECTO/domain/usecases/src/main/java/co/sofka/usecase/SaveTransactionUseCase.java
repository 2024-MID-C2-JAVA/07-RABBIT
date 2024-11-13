package co.sofka.usecase;



import co.sofka.Customer;
import co.sofka.Transaction;
import co.sofka.gateway.ICustomerRepository;
import co.sofka.gateway.ITransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SaveTransactionUseCase implements ISaveTransactionService {

    private static final Logger logger = LoggerFactory.getLogger(SaveTransactionUseCase.class);

    private final ITransactionRepository repository;

    public SaveTransactionUseCase(ITransactionRepository repository) {
        this.repository = repository;
    }


    public Transaction save(Transaction transaction) {

        return repository.save(transaction);
    }


}
