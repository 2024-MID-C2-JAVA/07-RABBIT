package com.bank.management;

import com.bank.management.data.CustomerDocument;
import com.bank.management.data.TransactionDocument;
import com.bank.management.gateway.TransactionRepository;
import com.bank.management.mapper.TransactionMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TransactionAdapter implements TransactionRepository {

    private final MongoTemplate mongoTemplate;

    public TransactionAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Transaction> save(Transaction trx, Account account, Customer customer, String role) {
        TransactionDocument transactionDocument = TransactionMapper.toDocument(trx);
        Query customerQuery = new Query(Criteria.where("_id").is(customer.getId())
                .and("accounts._id").is(account.getId()));

        CustomerDocument customerDocument = mongoTemplate.findOne(customerQuery, CustomerDocument.class);

        if (customerDocument == null) {
            return Optional.empty();
        }

        Update update = new Update().push("accounts.$.transactions", transactionDocument);

        mongoTemplate.updateFirst(customerQuery, update, CustomerDocument.class);

        return Optional.ofNullable(TransactionMapper.toDomain(transactionDocument));
    }

}
