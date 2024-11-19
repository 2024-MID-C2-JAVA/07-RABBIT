package com.bank.management.data;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Bank Account.
 */
public class ResponseAllBankAccountDTO {
    private final String number;
    private final BigDecimal amount;
    private final String id;
    private final boolean isDeleted;

    public ResponseAllBankAccountDTO(Builder builder) {
        this.number = builder.number;
        this.amount = builder.amount;
        this.id = builder.id;
        this.isDeleted = builder.isDeleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public String getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static class Builder {
        private String number;
        private BigDecimal amount;
        private String id;
        private boolean isDeleted;

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder isDeleted(Boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }


        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public ResponseAllBankAccountDTO build() {
            return new ResponseAllBankAccountDTO(this);
        }
    }

    @Override
    public String toString() {
        return "BankAccountDTO{" +
                "number='" + number + '\'' +
                ", amount=" + amount +
                '}';
    }
}
