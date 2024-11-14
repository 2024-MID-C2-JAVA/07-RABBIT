package co.sofka;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

    private String id;

    private BigDecimal amountTransaction;

    private BigDecimal transactionCost;

    private String typeTransaction;

    private LocalDateTime timeStamp;


    public Transaction(String id, BigDecimal amountTransaction, BigDecimal transactionCost, String typeTransaction, LocalDateTime timeStamp) {
        this.id = id;
        this.amountTransaction = amountTransaction;
        this.transactionCost = transactionCost;
        this.typeTransaction = typeTransaction;
        this.timeStamp = timeStamp;
    }

    public Transaction() {
    }

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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", amountTransaction=" + amountTransaction +
                ", transactionCost=" + transactionCost +
                ", typeTransaction='" + typeTransaction + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
