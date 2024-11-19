package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public class RequestPurchaseDTO {
    @NotBlank(message = "Account number cannot be empty or null")
    private String accountNumber;
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    @NotBlank(message = "Type cannot be empty or null")
    @Pattern(regexp = "PHYSICAL|ONLINE", message = "Type must be either 'PHYSICAL' or 'ONLINE'")
    private String type; // "PHYSICAL" or "ONLINE"

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setPurchaseType(String purchaseType) {
        this.type = purchaseType;
    }
}
