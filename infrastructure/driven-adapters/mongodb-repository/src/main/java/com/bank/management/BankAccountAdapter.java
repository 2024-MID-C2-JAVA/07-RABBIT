package com.bank.management;

import com.bank.management.data.AccountDocument;
import com.bank.management.data.CustomerDocument;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.mapper.AccountMapper;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BankAccountAdapter implements AccountRepository {

    private final MongoTemplate mongoTemplate;

    public BankAccountAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Account> findById(String id) {
        Query query = new Query(Criteria.where("accounts._id").is(id));

        query.fields().include("accounts.$");

        CustomerDocument customerDocument = mongoTemplate.findOne(query, CustomerDocument.class);

        if (customerDocument == null || customerDocument.getAccounts().isEmpty()) {
            return Optional.empty();
        }

        AccountDocument accountDocument = customerDocument.getAccounts().get(0);

        return Optional.ofNullable(AccountMapper.toDomain(accountDocument));
    }


    @Override
    public boolean delete(String id) {
        Query query = new Query(Criteria.where("accounts._id").is(id));

        Update update = new Update().set("accounts.$.isDeleted", true);

        return mongoTemplate.updateFirst(query, update, CustomerDocument.class).getModifiedCount() > 0;
    }

    @Override
    public Optional<Account> findByNumber(String accountNumber) {
        Query query = new Query(Criteria.where("accounts.number").is(accountNumber));

        CustomerDocument customerDocument = mongoTemplate.findOne(query, CustomerDocument.class);

        if (customerDocument == null) {
            return Optional.empty();
        }

        AccountDocument accountDocument = customerDocument.getAccounts().stream()
                .filter(account -> account.getNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);

        if (accountDocument == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(AccountMapper.toDomain(accountDocument));
    }

    @Override
    public List<Account> findByCustomerId(String id) {
        Query query = new Query(Criteria.where("_id").is(id));

        CustomerDocument customerDocument = mongoTemplate.findOne(query, CustomerDocument.class);

        if (customerDocument == null || customerDocument.getAccounts() == null) {
            return Collections.emptyList();
        }

        return customerDocument.getAccounts().stream()
                .map(AccountMapper::toDomain)
                .collect(Collectors.toList());
    }


    @Override
    public Optional<Account> save(Account account) {
        AccountDocument accountDocument = AccountMapper.toDocument(account);

        if (accountDocument.getId() == null) {
            accountDocument.setId(new ObjectId().toString());
        }

        if (account.getCustomer() != null) {
            Customer customer = account.getCustomer();

            Query query = new Query(Criteria.where("_id").is(customer.getId())
                    .and("accounts._id").is(accountDocument.getId()));

            Update update;
            if (mongoTemplate.exists(query, CustomerDocument.class)) {
                update = new Update()
                        .set("accounts.$.number", accountDocument.getNumber())
                        .set("accounts.$.amount", accountDocument.getAmount())
                        .set("accounts.$.customerId", accountDocument.getCustomerId())
                        .set("accounts.$.isDeleted", accountDocument.isDeleted());
            } else {
                query = new Query(Criteria.where("_id").is(customer.getId())); // Ajustamos la consulta al ID del cliente
                update = new Update().push("accounts", accountDocument);
            }

            mongoTemplate.updateFirst(query, update, CustomerDocument.class);
        }

        Account savedAccount = AccountMapper.toDomain(accountDocument);

        return Optional.of(savedAccount);
    }



}
