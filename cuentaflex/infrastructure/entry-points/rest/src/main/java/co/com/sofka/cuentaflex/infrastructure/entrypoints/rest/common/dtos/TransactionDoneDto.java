package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.dtos;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TransactionDoneDto {
    private String transactionId;
    private BigDecimal amount;
    private BigDecimal cost;
    private LocalDateTime timestamp;

    public TransactionDoneDto() {
    }

    public TransactionDoneDto(String transactionId, BigDecimal amount, BigDecimal cost, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.cost = cost;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
