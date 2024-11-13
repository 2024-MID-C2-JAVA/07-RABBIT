package co.com.sofka.cuentaflex.domain.models;

import co.com.sofka.shared.domain.models.BaseAuditableModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class Transaction extends BaseAuditableModel {
    private BigDecimal amount;
    private BigDecimal cost;
    private TransactionType type;
    private LocalDateTime timestamp;

    public Transaction(String id, BigDecimal amount, BigDecimal cost, TransactionType type) {
        super(id);
        this.amount = amount;
        this.cost = cost;
        this.type = type;
        this.timestamp = LocalDateTime.now();
    }

    public Transaction(String id, BigDecimal amount, BigDecimal cost, TransactionType type, LocalDateTime timestamp) {
        super(id);
        this.amount = amount;
        this.cost = cost;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Transaction(String id, LocalDateTime createdAt, BigDecimal amount, BigDecimal cost, TransactionType type, LocalDateTime timestamp) {
        super(id, createdAt);
        this.amount = amount;
        this.cost = cost;
        this.type = type;
        this.timestamp = timestamp;
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
