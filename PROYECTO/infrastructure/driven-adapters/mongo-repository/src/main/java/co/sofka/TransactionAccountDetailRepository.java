package co.sofka;


import co.sofka.data.entity.CustomerEntity;
import co.sofka.data.entity.TransactionAccountDetailEntity;
import co.sofka.data.entity.TransactionEntity;
import co.sofka.gateway.ITransactionAccountDetailRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class TransactionAccountDetailRepository implements ITransactionAccountDetailRepository {

    private static final Logger logger = LoggerFactory.getLogger(TransactionAccountDetailRepository.class);


    private final MongoTemplate mongoTemplate;


    @Override
    public TransactionAccountDetail save(TransactionAccountDetail id) {

        List<CustomerEntity> banktransaction = mongoTemplate.findAll(CustomerEntity.class, "banktransaction");

        banktransaction.stream().forEach(item->{
            item.getAccounts().stream().forEach(accountEntity -> {
                if (accountEntity.getNumber().equals(id.getAccount().getNumber())){
                    TransactionAccountDetailEntity transactionAccountDetailEntity = new TransactionAccountDetailEntity();
                    transactionAccountDetailEntity.setTransactionRole(id.getTransactionRole());

                    TransactionEntity transactionEntity = new TransactionEntity();
                    transactionEntity.setAmountTransaction(id.getTransaction().getAmountTransaction());
                    transactionEntity.setTransactionCost(id.getTransaction().getTransactionCost());
                    transactionEntity.setTypeTransaction(id.getTransaction().getTypeTransaction());
                    transactionEntity.setTimeStamp(id.getTransaction().getTimeStamp().toLocalDate());


                    transactionAccountDetailEntity.setTransaction(transactionEntity);
                    if (accountEntity.getTransactionAccountDetailEntity()==null){
                        accountEntity.setTransactionAccountDetailEntity(new ArrayList<>());
                    }
                    accountEntity.getTransactionAccountDetailEntity().add(transactionAccountDetailEntity);
                    mongoTemplate.save(item);
                }
            });
        });

        id.setId(UUID.randomUUID().toString());
        Transaction transaction = id.getTransaction();
        transaction.setId(UUID.randomUUID().toString());
        id.setTransaction(transaction);

        return id;
    }

    @Override
    public List<TransactionAccountDetail> getAll() {

        return null;
    }



}
