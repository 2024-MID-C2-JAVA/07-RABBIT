package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RequestDepositDTO {
    @NotBlank(message = "Username cannot be empty or null")
    private String Username;
    @NotBlank(message = "Account number cannot be empty or null")
    private String accountNumber;
    @NotNull(message = "Amount cannot be null")
    private BigDecimal amount;
    @NotBlank(message = "Transaction type cannot be empty or null")
    private String type;

    private RequestDepositDTO(Builder builder) {
        this.Username = builder.Username;
        this.accountNumber = builder.accountNumber;
        this.amount = builder.amount;
        this.type = builder.type;
    }

    private RequestDepositDTO(String Username, String accountNumber, BigDecimal amount, String type) {
        this.Username = Username;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
    }

    // Getters


    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return Username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public static class Builder {
        private String Username;
        private String accountNumber;
        private BigDecimal amount;
        private String type;

        public Builder setUsername(String Username) {
            this.Username = Username;
            return this;
        }

        public Builder setAccountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder setAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public RequestDepositDTO build() {
            return new RequestDepositDTO(this);
        }
    }

    @Override
    public String toString() {
        return "RequestDepositDTO{" +
                "Username=" + Username +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
