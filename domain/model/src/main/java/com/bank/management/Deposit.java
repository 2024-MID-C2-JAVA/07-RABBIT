package com.bank.management;

import java.math.BigDecimal;

public class Deposit {
    private final String username;
    private final String accountNumber;
    private final BigDecimal amount;
    private final String type;

    private Deposit(Builder builder) {
        this.username = builder.username;
        this.accountNumber = builder.accountNumber;
        this.amount = builder.amount;
        this.type = builder.type;
    }

    // Getters
    public String getusername() {
        return username;
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
        private String username;
        private String accountNumber;
        private BigDecimal amount;
        private String type;

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Deposit build() {
            return new Deposit(this);
        }
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "username=" + username +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
