package com.bank.management.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference
    private CustomerEntity customer;

    @OneToMany(mappedBy = "account")
    private List<TransactionAccountDetailEntity>  transactions;

    @Column(name = "created_at")
    @CreationTimestamp
    private Date createdAt;
    
    private boolean isDeleted;

    public AccountEntity() {}

    public AccountEntity(String number, BigDecimal amount, CustomerEntity customer) {
        this.number = number;
        this.amount = amount;
        this.customer = customer;
    }

    public void adjustBalance(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    // Getters y Setters


    public List<TransactionAccountDetailEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionAccountDetailEntity> transactions) {
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
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
