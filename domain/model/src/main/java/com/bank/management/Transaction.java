package com.bank.management;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Transaction {

    private String id;
    private BigDecimal amountTransaction;
    private BigDecimal transactionCost;
    private String typeTransaction;
    private Date timeStamp;
    private List<TransactionAccountDetail> transactionAccountDetails;

    private Transaction(Builder builder) {
        this.id = builder.id;
        this.amountTransaction = builder.amountTransaction;
        this.transactionCost = builder.transactionCost;
        this.typeTransaction = builder.typeTransaction;
        this.timeStamp = builder.timeStamp;
        this.transactionAccountDetails = builder.transactionAccountDetails;
    }

    // Getters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(BigDecimal amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    public BigDecimal getTransactionCost() {
        return transactionCost;
    }

    public void setTransactionCost(BigDecimal transactionCost) {
        this.transactionCost = transactionCost;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<TransactionAccountDetail> getTransactionAccountDetails() {
        return transactionAccountDetails;
    }

    public void setTransactionAccountDetails(List<TransactionAccountDetail> transactionAccountDetails) {
        this.transactionAccountDetails = transactionAccountDetails;
    }

    // Builder estático
    public static class Builder {
        private String id;
        private BigDecimal amountTransaction;
        private BigDecimal transactionCost;
        private String typeTransaction;
        private Date timeStamp;
        private List<TransactionAccountDetail> transactionAccountDetails;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder amountTransaction(BigDecimal amountTransaction) {
            this.amountTransaction = amountTransaction;
            return this;
        }

        public Builder transactionCost(BigDecimal transactionCost) {
            this.transactionCost = transactionCost;
            return this;
        }

        public Builder typeTransaction(String typeTransaction) {
            this.typeTransaction = typeTransaction;
            return this;
        }

        public Builder timeStamp(Date timeStamp) {
            this.timeStamp = timeStamp;
            return this;
        }

        public Builder transactionAccountDetails(List<TransactionAccountDetail> transactionAccountDetails) {
            this.transactionAccountDetails = transactionAccountDetails;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }
}
