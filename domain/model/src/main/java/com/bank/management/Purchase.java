package com.bank.management;

import java.math.BigDecimal;

public class Purchase {
    private final String accountNumber;
    private final BigDecimal amount;
    private final String type; // "PHYSICAL" or "ONLINE"

    private Purchase(Builder builder) {
        this.accountNumber = builder.accountNumber;
        this.amount = builder.amount;
        this.type = builder.type;
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
        private String accountNumber;
        private BigDecimal amount;
        private String type; // "PHYSICAL" or "ONLINE"

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

        public Purchase build() {
            return new Purchase(this);
        }
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", type='" + type + '\'' +
                '}';
    }
}
