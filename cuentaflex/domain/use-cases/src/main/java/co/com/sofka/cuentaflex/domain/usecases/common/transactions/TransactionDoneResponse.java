package co.com.sofka.cuentaflex.domain.usecases.common.transactions;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TransactionDoneResponse {
    private final String transactionId;
    private final BigDecimal amount;
    private final BigDecimal cost;
    private final LocalDateTime timestamp;

    public TransactionDoneResponse(String transactionId, BigDecimal amount, BigDecimal cost, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.cost = cost;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
