package com.bank.management;

import com.bank.management.data.CustomerDocument;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.mapper.CustomerMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CustomerAdapter implements CustomerRepository {


    private final MongoTemplate mongoTemplate;

    public CustomerAdapter(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerDocument> documents = mongoTemplate.findAll(CustomerDocument.class, "customer");
        return documents.stream()
                .map(CustomerMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> findById(String id) {
        CustomerDocument document = mongoTemplate.findById(id, CustomerDocument.class, "customer");
        if (document == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(CustomerMapper.toDomain(document));
    }

    @Override
    public Optional<Customer> save(Customer customer) {
        CustomerDocument customerDoc = CustomerMapper.toDocument(customer);
        CustomerDocument savedDocument = mongoTemplate.save(customerDoc, "customer");
        Customer savedCustomer = CustomerMapper.toDomain(savedDocument);
        return Optional.of(savedCustomer);
    }

    @Override
    public Optional<Customer> findByUsername(String username) {
        Query query = new Query(Criteria.where("username").is(username));
        CustomerDocument document = mongoTemplate.findOne(query, CustomerDocument.class, "customer");
        if (document == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(CustomerMapper.toDomain(document));
    }

    @Override
    public boolean delete(Customer customer) {
        CustomerDocument customerDoc = CustomerMapper.toDocument(customer);
        Query query = new Query(Criteria.where("id").is(customerDoc.getId()));
        Update update = new Update().set("isDeleted", true);
        return mongoTemplate.updateFirst(query, update, CustomerDocument.class).getModifiedCount() > 0;
    }

    @Override
    public Optional<Customer> findByNumber(String accountNumber) {
        Query query = new Query(Criteria.where("accounts.number").is(accountNumber));
        CustomerDocument document = mongoTemplate.findOne(query, CustomerDocument.class, "customer");
        if (document == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(CustomerMapper.toDomain(document));
    }
}
