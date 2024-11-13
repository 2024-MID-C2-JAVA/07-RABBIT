package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.account;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class MongoAccountDocument {
    private String id;
    private int number;
    private BigDecimal amount;
    private LocalDateTime createdAt;
    private boolean isDeleted;
    private List<MongoAccountTransactionDocument> transactions;

    public MongoAccountDocument() {
    }

    public MongoAccountDocument(String id, int number, BigDecimal amount, LocalDateTime createdAt, boolean isDeleted, List<MongoAccountTransactionDocument> transactions) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<MongoAccountTransactionDocument> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<MongoAccountTransactionDocument> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(MongoAccountTransactionDocument transaction) {
        this.transactions.add(transaction);
    }
}
