package co.sofka;



import co.sofka.config.JpaTransactionAccountDetailRepository;
import co.sofka.config.JpaTransactionRepository;
import co.sofka.data.entity.AccountEntity;
import co.sofka.data.entity.TransactionAccountDetailEntity;
import co.sofka.data.entity.TransactionEntity;
import co.sofka.gateway.ITransactionAccountDetailRepository;
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
public class TransactionAccountDetailRepository implements ITransactionAccountDetailRepository {

    private static final Logger logger = LoggerFactory.getLogger(TransactionAccountDetailRepository.class);

    private final JpaTransactionAccountDetailRepository repository;

    private final TransactionRepository transactionRepository;


    @Transactional
    @Override
    public TransactionAccountDetail save(TransactionAccountDetail id) {

        Transaction save1 = transactionRepository.save(id.getTransaction());
        id.setTransaction(save1);

        TransactionAccountDetailEntity transactionEntity = toTransactionAccountDetailEntity(id);
        logger.info("TransactionEntity: "+transactionEntity.getAccount().getId());

        TransactionAccountDetailEntity save = repository.save(transactionEntity);
        repository.flush();
        return toTransaction(save);
    }

    @Override
    public List<TransactionAccountDetail> getAll() {
        List<TransactionAccountDetailEntity> all = repository.findAll();

        return null;
    }



    public TransactionAccountDetail toTransaction(TransactionAccountDetailEntity customerEntity){
        TransactionAccountDetail transaction = new TransactionAccountDetail();

        transaction.setAccount(new Account());
        transaction.setTransaction(new Transaction());

        transaction.setId(customerEntity.getId().toString());
        transaction.setTransactionRole(customerEntity.getTransactionRole());


        return transaction;
    }

    public TransactionAccountDetailEntity toTransactionAccountDetailEntity(TransactionAccountDetail customerEntity){
        TransactionAccountDetailEntity transaction = new TransactionAccountDetailEntity();
        TransactionEntity transactionEntity = new TransactionEntity();
        transactionEntity.setId(Long.parseLong(customerEntity.getTransaction().getId()));
        transaction.setTransaction(transactionEntity);
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(Long.parseLong(customerEntity.getAccount().getId()));

        transaction.setAccount(accountEntity);
        if(customerEntity.getId() != null){
            transaction.setId(Long.parseLong(customerEntity.getId()));
        }
        transaction.setTransactionRole(customerEntity.getTransactionRole());


        return transaction;
    }

//    public List<Transaction> toTransactions(List<TransactionEntity> customerEntity){
//        List<Transaction> itesms = new ArrayList<>();
//        itesms = customerEntity.stream().map(item->{
//            Transaction transaction = toTransaction(item);
//            return transaction;
//
//        }).collect(Collectors.toList());
//
//        return itesms;
//    }



}
