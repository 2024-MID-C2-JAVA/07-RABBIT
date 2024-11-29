package co.com.sofka.cuentaflex.infrastructure.drivenadapters.mongorepository.account;

import co.com.sofka.cuentaflex.domain.models.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MongoTransactionDocument {
    private String id;
    private BigDecimal amount;
    private BigDecimal cost;
    private TransactionType type;
    private LocalDateTime timestamp;

    public MongoTransactionDocument() {
    }

    public MongoTransactionDocument(String id, BigDecimal amount, BigDecimal cost, TransactionType type, LocalDateTime timestamp) {
        this.id = id;
        this.amount = amount;
        this.cost = cost;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
