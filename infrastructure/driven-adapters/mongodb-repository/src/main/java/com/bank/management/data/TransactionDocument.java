package com.bank.management.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.Date;

@Document(collection = "transaction")
public class TransactionDocument {

    @Id
    private String id;

    @Field("amount_transaction")
    private BigDecimal amountTransaction;

    @Field("transaction_cost")
    private BigDecimal transactionCost;

    @Field("type_transaction")
    private String typeTransaction;

    @Field("time_stamp")
    private Date timeStamp;

    public TransactionDocument() {}

    public TransactionDocument(BigDecimal amountTransaction, BigDecimal transactionCost, String typeTransaction, Date timeStamp) {
        this.amountTransaction = amountTransaction;
        this.transactionCost = transactionCost;
        this.typeTransaction = typeTransaction;
        this.timeStamp = timeStamp;
    }

    // Getters y Setters
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
}
