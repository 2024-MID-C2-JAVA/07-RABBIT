package com.bank.management;

import java.math.BigDecimal;

public class Withdrawal {
    private final String username;
    private final String accountNumber;
    private final BigDecimal amount;

    private Withdrawal(Builder builder) {
        this.username = builder.username;
        this.accountNumber = builder.accountNumber;
        this.amount = builder.amount;
    }

    public Withdrawal(String username, String accountNumber, BigDecimal amount) {
        this.username = username;
        this.accountNumber = accountNumber;
        this.amount = amount;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private String username;
        private String accountNumber;
        private BigDecimal amount;

        public Builder setUsername(String username) {
            this.username = username;
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

        public Withdrawal build() {
            return new Withdrawal(this);
        }
    }

    @Override
    public String toString() {
        return "Withdrawal{" +
                "username=" + username +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
