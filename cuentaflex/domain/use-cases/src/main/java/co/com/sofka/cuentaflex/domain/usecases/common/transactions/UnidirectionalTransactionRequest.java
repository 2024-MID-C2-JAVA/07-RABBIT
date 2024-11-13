package co.com.sofka.cuentaflex.domain.usecases.common.transactions;

import java.math.BigDecimal;

public final class UnidirectionalTransactionRequest {
    private final String customerId;
    private final String accountId;
    private final BigDecimal amount;

    public UnidirectionalTransactionRequest(String customerId, String accountId, BigDecimal amount) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}
