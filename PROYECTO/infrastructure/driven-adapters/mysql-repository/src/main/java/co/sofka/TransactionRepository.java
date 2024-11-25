package co.sofka;



import co.sofka.config.JpaTransactionRepository;
import co.sofka.data.entity.TransactionEntity;
import co.sofka.gateway.ITransactionRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class TransactionRepository implements ITransactionRepository {

    private static final Logger logger = LoggerFactory.getLogger(TransactionRepository.class);

    private final JpaTransactionRepository repository;


    @Transactional
    @Override
    public Transaction save(Transaction id) {
        TransactionEntity transactionEntity = toTransactionEntity(id);
        TransactionEntity save = repository.save(transactionEntity);
        repository.flush();
        return toTransaction(save);
    }

    @Override
    public List<Transaction> getAll() {
        List<TransactionEntity> all = repository.findAll();

        return toTransactions(all);
    }



    public Transaction toTransaction(TransactionEntity customerEntity){
        Transaction transaction = new Transaction();
        transaction.setAmountTransaction(customerEntity.getAmountTransaction());
        transaction.setTypeTransaction(customerEntity.getTypeTransaction());
        transaction.setId(customerEntity.getId().toString());
        transaction.setTimeStamp(customerEntity.getTimeStamp());
        transaction.setTransactionCost(customerEntity.getTransactionCost());


        return transaction;
    }

    public TransactionEntity toTransactionEntity(Transaction customerEntity){
        TransactionEntity transaction = new TransactionEntity();
        transaction.setAmountTransaction(customerEntity.getAmountTransaction());
        transaction.setTypeTransaction(customerEntity.getTypeTransaction());
        if(customerEntity.getId() != null){
            transaction.setId(Long.parseLong(customerEntity.getId()));
        }
        transaction.setTimeStamp(customerEntity.getTimeStamp());
        transaction.setTransactionCost(customerEntity.getTransactionCost());


        return transaction;
    }

    public List<Transaction> toTransactions(List<TransactionEntity> customerEntity){
        List<Transaction> itesms = new ArrayList<>();
        itesms = customerEntity.stream().map(item->{
            Transaction transaction = toTransaction(item);
            return transaction;

        }).collect(Collectors.toList());

        return itesms;
    }



}
