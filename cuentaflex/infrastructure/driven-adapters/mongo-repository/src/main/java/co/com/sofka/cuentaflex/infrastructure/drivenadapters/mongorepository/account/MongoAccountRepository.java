package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.account;

import co.com.sofka.cuentaflex.domain.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.domain.models.Account;
import co.com.sofka.cuentaflex.domain.models.AccountMovement;
import co.com.sofka.cuentaflex.domain.models.Transaction;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.customer.MongoCustomerDocument;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class MongoAccountRepository implements AccountRepository {
    private final MongoTemplate mongoTemplate;

    public MongoAccountRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Account createAccount(Account account) {
        String accountId = new ObjectId().toHexString();
        MongoAccountDocument document = MongoAccountMapper.toDocument(account);
        document.setId(accountId);
        document.setNumber(this.getTotalAccountsCount() + 1);
        MongoCustomerDocument customerDocument = mongoTemplate.findById(account.getCustomerId(), MongoCustomerDocument.class);

        if (customerDocument == null) {
            throw new NoSuchElementException("Customer not found");
        }

        if (customerDocument.getAccounts() == null) {
            customerDocument.setAccounts(List.of(document));
        } else {
            customerDocument.getAccounts().add(document);
        }

        mongoTemplate.save(customerDocument);

        return MongoAccountMapper.toModel(document);
    }

    private int getTotalAccountsCount() {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.unwind("accounts"),
                Aggregation.count().as("totalAccounts")
        );

        AggregationResults<Map> result = mongoTemplate.aggregate(aggregation, MongoCustomerDocument.class, Map.class);

        return result.getMappedResults().isEmpty() ? 0 : ((Number) result.getMappedResults().getFirst().get("totalAccounts")).intValue();
    }

    @Override
    public Account getByIdAndCustomerId(String accountId, String customerId) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("id")
                        .is(customerId)
                        .and("accounts.id")
                        .is(accountId)
        );

        MongoCustomerDocument customerDocument = mongoTemplate.findOne(query, MongoCustomerDocument.class);

        if (customerDocument == null) {
            return null;
        }

        MongoAccountDocument accountDocument = customerDocument.getAccounts().stream().filter(
                account -> account.getId().equals(accountId)).findFirst().orElse(null);

        if (accountDocument == null) {
            return null;
        }

        return MongoAccountMapper.toModel(accountDocument);
    }

    @Override
    public Account getByAccountNumber(int number) {
        Query query = new Query();
        query.addCriteria(
                Criteria.where("accounts.number").is(number)
        );

        MongoCustomerDocument customerDocument = mongoTemplate.findOne(query, MongoCustomerDocument.class);

        if (customerDocument == null) {
            return null;
        }

        MongoAccountDocument accountDocument = customerDocument.getAccounts().stream()
                .filter(account -> account.getNumber() == number)
                .findFirst()
                .orElse(null);

        if (accountDocument == null) {
            return null;
        }

        return MongoAccountMapper.toModel(accountDocument);
    }

    @Override
    @Transactional
    public List<Account> updateMany(Account... accounts) {
        Map<Transaction, MongoTransactionDocument> savedTransactions = new HashMap<>();
        List<MongoAccountDocument> updatedAccounts = new ArrayList<>();

        for (Account account : accounts) {
            MongoCustomerDocument customerDocument = findCustomerByAccountId(account.getId());

            MongoAccountDocument accountDocument = findAccountById(customerDocument, account.getId());

            updateAccountDetails(accountDocument, account);

            if (isEmptyTransactionHistory(account)) {
                saveAccount(customerDocument, accountDocument);
                updatedAccounts.add(accountDocument);
                continue;
            }

            for (AccountMovement movement : account.getTransactionHistory()) {
                MongoTransactionDocument transactionDocument = processTransaction(movement, accountDocument, savedTransactions);
                addTransactionToAccount(accountDocument, transactionDocument, movement);
            }

            saveAccount(customerDocument, accountDocument);
            updatedAccounts.add(accountDocument);
        }

        return updatedAccounts.stream().map(MongoAccountMapper::toModel).toList();
    }

    private MongoCustomerDocument findCustomerByAccountId(String accountId) {
        Query query = new Query(Criteria.where("accounts.id").is(accountId));
        MongoCustomerDocument customerDocument = mongoTemplate.findOne(query, MongoCustomerDocument.class);
        if (customerDocument == null) {
            throw new NoSuchElementException("Customer not found");
        }
        return customerDocument;
    }

    private MongoAccountDocument findAccountById(MongoCustomerDocument customerDocument, String accountId) {
        return customerDocument.getAccounts().stream()
                .filter(account -> account.getId().equals(accountId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Account not found"));
    }

    private void updateAccountDetails(MongoAccountDocument accountDocument, Account account) {
        accountDocument.setAmount(account.getAmount());
        accountDocument.setDeleted(account.isDeleted());
    }

    private boolean isEmptyTransactionHistory(Account account) {
        return account.getTransactionHistory() == null || account.getTransactionHistory().getMovementCount() <= 0;
    }

    private void saveAccount(MongoCustomerDocument customerDocument, MongoAccountDocument accountDocument) {
        mongoTemplate.save(customerDocument);
    }

    private MongoTransactionDocument processTransaction(AccountMovement movement, MongoAccountDocument accountDocument, Map<Transaction, MongoTransactionDocument> savedTransactions) {
        Transaction transaction = movement.getTransaction();
        MongoTransactionDocument transactionDocument;

        if (transaction.getId() == null || transaction.getId().isEmpty()) {
            transactionDocument = savedTransactions.computeIfAbsent(transaction, this::createAndSaveTransactionDocument);
        } else {
            MongoAccountTransactionDocument fetchedAccountTransactionDocument = accountDocument.getTransactions().stream()
                    .filter(doc -> doc.getTransaction().getId().equals(transaction.getId()))
                    .findFirst()
                    .orElse(null);

            MongoTransactionDocument fetchedTransactionDocument = fetchedAccountTransactionDocument == null ? null : fetchedAccountTransactionDocument.getTransaction();

            if (fetchedTransactionDocument == null) {
                transactionDocument = createAndSaveTransactionDocument(transaction);
                savedTransactions.put(transaction, transactionDocument);
            } else {
                transactionDocument = updateTransactionDocument(fetchedTransactionDocument, transaction);
            }
        }

        return transactionDocument;
    }

    private MongoTransactionDocument updateTransactionDocument(MongoTransactionDocument transactionDocument, Transaction transaction) {
        transactionDocument.setAmount(transaction.getAmount());
        transactionDocument.setCost(transaction.getCost());
        transactionDocument.setType(transaction.getType());
        transactionDocument.setTimestamp(transaction.getTimestamp());
        return transactionDocument;
    }

    private MongoTransactionDocument createAndSaveTransactionDocument(Transaction transaction) {
        MongoTransactionDocument transactionDocument = new MongoTransactionDocument();
        transactionDocument.setId(new ObjectId().toHexString());
        transactionDocument.setAmount(transaction.getAmount());
        transactionDocument.setCost(transaction.getCost());
        transactionDocument.setType(transaction.getType());
        transactionDocument.setTimestamp(transaction.getTimestamp());
        return transactionDocument;
    }

    private void addTransactionToAccount(MongoAccountDocument accountDocument, MongoTransactionDocument transactionDocument, AccountMovement movement) {
        MongoAccountTransactionDocument accountTransactionDocument = new MongoAccountTransactionDocument(transactionDocument, movement.getRole());

        if (accountDocument.getTransactions() == null) {
            accountDocument.setTransactions(List.of(accountTransactionDocument));
        } else {
            if (accountDocument.getTransactions().stream().anyMatch(doc -> doc.getTransaction().getId().equals(transactionDocument.getId()))) {
                return;
            }
            accountDocument.getTransactions().add(accountTransactionDocument);
        }
    }
}