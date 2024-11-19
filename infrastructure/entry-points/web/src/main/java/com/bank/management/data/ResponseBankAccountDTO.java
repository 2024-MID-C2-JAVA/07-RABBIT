package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Bank Account.
 */
public class ResponseBankAccountDTO {
    private final String number;
    private final BigDecimal amount;
    private final String id;
    private final boolean isDeleted;

    private ResponseBankAccountDTO(Builder builder) {
        this.number = builder.number;
        this.amount = builder.amount;
        this.id = builder.id;
        this.isDeleted = builder.isDeleted;
    }

    public String getId() {
        return id;
    }

    public boolean isDeleted() {
        return isDeleted;
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
        public Builder id(String id) {
            this.id = id;
            return this;
        }
        public Builder isDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public ResponseBankAccountDTO build() {
            return new ResponseBankAccountDTO(this);
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
