package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.account;

import co.com.sofka.cuentaflex.domain.models.AccountRole;
import co.com.sofka.cuentaflex.domain.models.Transaction;

public class MongoAccountTransactionDocument {
    private MongoTransactionDocument transaction;
    private AccountRole role;

    public MongoAccountTransactionDocument() {
    }

    public MongoAccountTransactionDocument(MongoTransactionDocument transaction, AccountRole role) {
        this.transaction = transaction;
        this.role = role;
    }

    public MongoTransactionDocument getTransaction() {
        return transaction;
    }

    public void setTransaction(MongoTransactionDocument transaction) {
        this.transaction = transaction;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }
}
