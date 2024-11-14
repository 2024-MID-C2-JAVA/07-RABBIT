package com.bank.management.data;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount_transaction", nullable = false)
    private BigDecimal amountTransaction;

    @Column(name = "transaction_cost", nullable = false)
    private BigDecimal transactionCost;

    @Column(name = "type_transaction", nullable = false)
    private String typeTransaction; // Ejemplo: "DEPOSIT", "WITHDRAWAL"

    @OneToMany(mappedBy = "transaction")
    private List<TransactionAccountDetailEntity>  accounts;

    @Column(name = "time_stamp", nullable = false)
    @CreationTimestamp
    private Date timeStamp;

    // Constructor vacío
    public TransactionEntity() {}

    // Constructor con parámetros
    public TransactionEntity(BigDecimal amountTransaction, BigDecimal transactionCost, String typeTransaction, Date timeStamp) {
        this.amountTransaction = amountTransaction;
        this.transactionCost = transactionCost;
        this.typeTransaction = typeTransaction;
        this.timeStamp = timeStamp;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
