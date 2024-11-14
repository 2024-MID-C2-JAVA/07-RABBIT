package com.bank.management.data;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "account")
public class AccountDocument {

    @Id
    private String id;

    private String number;

    private BigDecimal amount;

    @Field("customer_id")
    private String customerId;

    @Field("transactions")
    private List<TransactionDocument> transactions = new ArrayList<>();

    @Field("created_at")
    private Date createdAt;

    @Field("is_deleted")
    private boolean isDeleted;

    public AccountDocument() {}

    public AccountDocument(String number, BigDecimal amount, String customerId, List<TransactionDocument> transactions) {
        this.number = number;
        this.amount = amount;
        this.customerId = customerId;
        this.createdAt = new Date();
        this.transactions = (transactions != null) ? transactions : new ArrayList<>();
    }

    public void adjustBalance(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    // Getters y Setters


    public List<TransactionDocument> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDocument> transactions) {
        this.transactions = transactions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
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
}
