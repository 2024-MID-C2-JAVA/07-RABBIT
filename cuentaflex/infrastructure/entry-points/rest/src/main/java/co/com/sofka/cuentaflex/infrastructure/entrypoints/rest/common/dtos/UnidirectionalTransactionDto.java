package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.common.dtos;

import java.math.BigDecimal;

public final  class UnidirectionalTransactionDto {
    private String customerId;
    private String accountId;
    private BigDecimal amount;

    public UnidirectionalTransactionDto() {
    }

    public UnidirectionalTransactionDto(String customerId, String accountId, BigDecimal amount) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
