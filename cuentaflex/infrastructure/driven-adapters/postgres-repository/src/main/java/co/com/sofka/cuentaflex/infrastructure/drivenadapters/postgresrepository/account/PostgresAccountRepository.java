package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.account;

import co.com.sofka.cuentaflex.domain.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.domain.models.Account;
import co.com.sofka.cuentaflex.domain.models.AccountMovement;
import co.com.sofka.cuentaflex.domain.models.Transaction;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerEntity;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerRepository;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class PostgresAccountRepository implements AccountRepository {
    private final JpaAccountRepository jpaAccountRepository;
    private final JpaCustomerRepository jpaCustomerRepository;
    private final JpaTransactionRepository jpaTransactionRepository;
    private final JpaAccountTransactionRepository jpaAccountTransactionRepository;

    public PostgresAccountRepository(
            JpaAccountRepository jpaAccountRepository,
            JpaCustomerRepository jpaCustomerRepository,
            JpaTransactionRepository jpaTransactionRepository,
            JpaAccountTransactionRepository jpaAccountTransactionRepository
    ) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.jpaCustomerRepository = jpaCustomerRepository;
        this.jpaTransactionRepository = jpaTransactionRepository;
        this.jpaAccountTransactionRepository = jpaAccountTransactionRepository;
    }

    @Override
    @Transactional
    public Account createAccount(Account account) {
        JpaCustomerEntity customerEntity = this.jpaCustomerRepository.findById(Integer.parseInt(account.getCustomerId())).get();

        JpaAccountEntity accountEntity = JpaAccountMapper.fromModelToJpaAccount(account);

        accountEntity.setCustomer(customerEntity);
        accountEntity.setNumber((int) this.jpaAccountRepository.count() + 1);

        return JpaAccountMapper.fromJpaToModelAccount(this.jpaAccountRepository.save(accountEntity));
    }

    @Override
    public Account getByIdAndCustomerId(String accountId, String customerId) {
        int accountIdInt = Integer.parseInt(accountId);
        int customerIdInt = Integer.parseInt(customerId);

        Optional<JpaAccountEntity> accountEntity = this.jpaAccountRepository.findByIdAndCustomer_Id(accountIdInt, customerIdInt);

        return accountEntity.map(JpaAccountMapper::fromJpaToModelAccount).orElse(null);
    }

    @Override
    public Account getByAccountNumber(int number) {
        Optional<JpaAccountEntity> accountEntity = this.jpaAccountRepository.findByNumber(number);
        return accountEntity.map(JpaAccountMapper::fromJpaToModelAccount).orElse(null);
    }

    @Override
    @Transactional
    public List<Account> updateMany(Account... accounts) {
        Map<Transaction, JpaTransactionEntity> createdTransactionEntitiesMap = new HashMap<>();
        List<JpaAccountEntity> updatedAccounts = new ArrayList<>();

        for (Account account : accounts) {
            JpaAccountEntity accountEntity = jpaAccountRepository.findById(Integer.parseInt(account.getId())).get();
            updateAccountDetails(accountEntity, account);
            handleAccountTransactions(account, accountEntity, createdTransactionEntitiesMap);
            updatedAccounts.add(jpaAccountRepository.save(accountEntity));
        }

        return updatedAccounts.stream()
                .map(JpaAccountMapper::fromJpaToModelAccount)
                .toList();
    }

    private void updateAccountDetails(JpaAccountEntity accountEntity, Account account) {
        accountEntity.setAmount(account.getAmount());
        accountEntity.setDeleted(account.isDeleted());
    }

    private void handleAccountTransactions(
            Account account,
            JpaAccountEntity accountEntity,
            Map<Transaction, JpaTransactionEntity> createdTransactionEntitiesMap
    ) {
        for (AccountMovement movement : account.getTransactionHistory()) {
            Transaction transaction = movement.getTransaction();
            JpaTransactionEntity transactionEntity = getOrCreateTransactionEntity(transaction, createdTransactionEntitiesMap);
            createOrUpdateAccountTransaction(accountEntity, transactionEntity, movement);
        }
    }

    private JpaTransactionEntity getOrCreateTransactionEntity(
            Transaction transaction,
            Map<Transaction, JpaTransactionEntity> createdTransactionEntitiesMap
    ) {
        if (transaction.getId() == null || transaction.getId().isEmpty()) {
            return createdTransactionEntitiesMap.computeIfAbsent(transaction, this::createAndSaveTransactionEntity);
        }

        int transactionId = Integer.parseInt(transaction.getId());

        return jpaTransactionRepository.findById(transactionId)
                .orElseGet(() -> {
                    JpaTransactionEntity newTransactionEntity = createAndSaveTransactionEntity(transaction);
                    createdTransactionEntitiesMap.put(transaction, newTransactionEntity);
                    return newTransactionEntity;
                });
    }

    private JpaTransactionEntity createAndSaveTransactionEntity(Transaction transaction) {
        JpaTransactionEntity transactionEntity = new JpaTransactionEntity();
        transactionEntity.setAmount(transaction.getAmount());
        transactionEntity.setCost(transaction.getCost());
        transactionEntity.setType(transaction.getType());
        transactionEntity.setTimestamp(transaction.getTimestamp());
        return jpaTransactionRepository.save(transactionEntity);
    }

    private void createOrUpdateAccountTransaction(
            JpaAccountEntity accountEntity,
            JpaTransactionEntity transactionEntity,
            AccountMovement movement
    ) {
        JpaAccountTransactionId relationshipId = new JpaAccountTransactionId();
        relationshipId.setAccountId(accountEntity.getId());
        relationshipId.setTransactionId(transactionEntity.getId());

        JpaAccountTransactionEntity accountTransactionEntity = new JpaAccountTransactionEntity();
        accountTransactionEntity.setId(relationshipId);
        accountTransactionEntity.setAccount(accountEntity);
        accountTransactionEntity.setTransaction(transactionEntity);
        accountTransactionEntity.setRole(movement.getRole());

        jpaAccountTransactionRepository.save(accountTransactionEntity);
        accountEntity.addAccountTransaction(accountTransactionEntity);
    }

}
