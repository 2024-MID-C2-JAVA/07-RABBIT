package com.bank.management;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Account {

    private String id;
    private Customer customer;
    private BigDecimal amount;
    private String number;
    private boolean isDeleted;
    private List<TransactionAccountDetail> transactionAccountDetails;
    private Date created_at;

    private Account(Builder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.number = builder.number;
        this.isDeleted = builder.isDeleted;
        this.created_at = builder.created_at != null ? builder.created_at : new Date(System.currentTimeMillis());
        this.customer = builder.customer;
        this.transactionAccountDetails = builder.transactionAccountDetails != null ? builder.transactionAccountDetails : new ArrayList<>(); // Inicializar lista
    }

    public Account() {
    }

    public Account(String id, BigDecimal amount, String number, List<TransactionAccountDetail> transactionAccountDetails, boolean isDeleted, Date created_at, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.amount = amount;
        this.number = number;
        this.isDeleted = isDeleted;
        this.transactionAccountDetails = transactionAccountDetails != null ? transactionAccountDetails : new ArrayList<>();
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public List<TransactionAccountDetail> getTransactionAccountDetails() {
        return transactionAccountDetails;
    }

    public void setTransactionAccountDetails(List<TransactionAccountDetail> transactionAccountDetails) {
        this.transactionAccountDetails = transactionAccountDetails;
    }

    public void adjustBalance(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public static class Builder {
        private String id;
        private Customer customer;
        private BigDecimal amount;
        private String number;
        private boolean isDeleted;
        private Date created_at;
        private List<TransactionAccountDetail> transactionAccountDetails;

        public Builder() {}

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder customer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder amount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder isDeleted(boolean isDeleted) {
            this.isDeleted = isDeleted;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder createdAt(Date created_at) {
            this.created_at = created_at;
            return this;
        }

        public Builder transactionAccountDetails(List<TransactionAccountDetail> transactionAccountDetails) {
            this.transactionAccountDetails = transactionAccountDetails;
            return this;
        }

        public Account build() {
            return new Account(this);
        }
    }
}
