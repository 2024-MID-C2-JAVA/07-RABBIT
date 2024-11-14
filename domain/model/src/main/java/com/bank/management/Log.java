package com.bank.management;

public class Log {
    private String id;
    private String amountTransaction;
    private String transactionCost;
    private String typeTransaction;
    private String timeStamp;

    public String getId() {
        return id;
    }

    public String getTransactionCost() {
        return transactionCost;
    }

    public void setTransactionCost(String transactionCost) {
        this.transactionCost = transactionCost;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAmountTransaction() {
        return amountTransaction;
    }

    public void setAmountTransaction(String amountTransaction) {
        this.amountTransaction = amountTransaction;
    }

    public String getTypeTransaction() {
        return typeTransaction;
    }

    public void setTypeTransaction(String typeTransaction) {
        this.typeTransaction = typeTransaction;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
