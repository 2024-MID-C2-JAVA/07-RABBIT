package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Bank Account.
 */
public class RequestCreateAccountDTO {

    @NotBlank(message = "Customer ID cannot be empty or null")
    private String customerId;
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;

    public RequestCreateAccountDTO() {}

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                ", customerId=" + customerId +
                ", amount=" + amount +
                '}';
    }
}
