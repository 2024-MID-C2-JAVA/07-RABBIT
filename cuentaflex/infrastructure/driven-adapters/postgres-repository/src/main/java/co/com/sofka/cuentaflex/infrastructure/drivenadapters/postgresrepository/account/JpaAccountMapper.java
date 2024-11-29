package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.account;

import co.com.sofka.cuentaflex.domain.models.Account;
import co.com.sofka.cuentaflex.domain.models.AccountMovement;
import co.com.sofka.cuentaflex.domain.models.Transaction;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction.JpaAccountTransactionEntity;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction.JpaTransactionEntity;

public final class JpaAccountMapper {
    public static Account fromJpaToModelAccount(JpaAccountEntity jpaAccountEntity) {
        String customerId = jpaAccountEntity.getCustomer() == null ? "" : jpaAccountEntity.getCustomer().getId() + "";
        Account account = new Account(
                jpaAccountEntity.getId() + "",
                jpaAccountEntity.getCreatedAt(),
                jpaAccountEntity.getNumber(),
                jpaAccountEntity.getAmount(),
                customerId,
                jpaAccountEntity.isDeleted()
        );
        if (jpaAccountEntity.getAccountTransactionEntities() == null) {
            return account;
        }

        for (JpaAccountTransactionEntity relationship : jpaAccountEntity.getAccountTransactionEntities()) {
            JpaTransactionEntity transactionEntity = relationship.getTransaction();

            account.addAccountMovement(
                    new AccountMovement(
                            new Transaction(
                                    transactionEntity.getId() + "",
                                    transactionEntity.getAmount(),
                                    transactionEntity.getCost(),
                                    transactionEntity.getType(),
                                    transactionEntity.getTimestamp()
                            ),
                            relationship.getRole()
                    )
            );
        }

        return account;
    }

    public static JpaAccountEntity fromModelToJpaAccount(Account account) {
        JpaAccountEntity entity = new JpaAccountEntity();

        int id = account.getId() == null ? 0 : Integer.parseInt(account.getId());
        entity.setId(id);
        entity.setNumber(account.getNumber());
        entity.setAmount(account.getAmount());
        entity.setCreatedAt(account.getCreatedAt());
        entity.setDeleted(account.isDeleted());

        return entity;
    }
}
