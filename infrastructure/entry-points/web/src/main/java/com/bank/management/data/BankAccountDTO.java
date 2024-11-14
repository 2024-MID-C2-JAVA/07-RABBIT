package com.bank.management.data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Data Transfer Object for Bank Account.
 */
public class BankAccountDTO {
    @NotBlank(message = "Account number cannot be empty or null")
    private final String number;
    @NotNull(message = "Amount cannot be null")
    private final BigDecimal amount;

    private BankAccountDTO(Builder builder) {
        this.number = builder.number;
        this.amount = builder.amount;
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

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public BankAccountDTO build() {
            return new BankAccountDTO(this);
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
