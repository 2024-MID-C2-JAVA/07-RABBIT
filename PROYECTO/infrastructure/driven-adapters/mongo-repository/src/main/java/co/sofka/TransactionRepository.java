package co.sofka;


import co.sofka.config.JpaBanktransactionRepository;
import co.sofka.gateway.ITransactionRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class TransactionRepository implements ITransactionRepository {

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    private final JpaBanktransactionRepository repository;


    @Override
    public Transaction save(Transaction id) {
       return null;
    }

    @Override
    public List<Transaction> getAll() {
        return null;
    }


}
