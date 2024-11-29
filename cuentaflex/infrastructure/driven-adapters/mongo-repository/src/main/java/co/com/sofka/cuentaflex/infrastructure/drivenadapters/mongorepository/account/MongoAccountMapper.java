package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.account;

import co.com.sofka.cuentaflex.domain.models.Account;
import co.com.sofka.cuentaflex.domain.models.AccountMovement;
import co.com.sofka.cuentaflex.domain.models.Transaction;

public final class MongoAccountMapper {
    public static MongoAccountDocument toDocument(Account account) {
        MongoAccountDocument accountDocument = new MongoAccountDocument(
                account.getId(),
                account.getNumber(),
                account.getAmount(),
                account.getCreatedAt(),
                account.isDeleted(),
                null
        );

        if (account.getTransactionHistory() == null || account.getTransactionHistory().getMovementCount() <= 0) {
            return accountDocument;
        }

        for (AccountMovement movement : account.getTransactionHistory()) {
            Transaction transaction = movement.getTransaction();

            if(transaction == null) {
                continue;
            }

            MongoAccountTransactionDocument transactionDocument = new MongoAccountTransactionDocument(
                    new MongoTransactionDocument(
                            transaction.getId(),
                            transaction.getAmount(),
                            transaction.getCost(),
                            transaction.getType(),
                            transaction.getTimestamp()
                    ),
                    movement.getRole()
            );

            accountDocument.addTransaction(transactionDocument);
        }

        return accountDocument;
    }

    public static Account toModel(MongoAccountDocument document) {
        Account account = new Account(
                document.getId(),
                document.getCreatedAt(),
                document.getNumber(),
                document.getAmount(),
                null,
                document.isDeleted()
        );

        if(document.getTransactions() == null || document.getTransactions().size() <= 0) {
            return account;
        }

        for (MongoAccountTransactionDocument transactionDocument : document.getTransactions()) {
            account.addAccountMovement(
                    new AccountMovement(
                            new Transaction(
                                    transactionDocument.getTransaction().getId(),
                                    transactionDocument.getTransaction().getAmount(),
                                    transactionDocument.getTransaction().getCost(),
                                    transactionDocument.getTransaction().getType(),
                                    transactionDocument.getTransaction().getTimestamp()
                            ),
                            transactionDocument.getRole()
                    )
            );
        }

        return account;
    }
}
