package com.bank.management.data;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TransactionAccountDetailId implements Serializable {

    private Long transactionId;
    private Long accountId;

    public TransactionAccountDetailId() {}

    public TransactionAccountDetailId(Long transactionId, Long accountId) {
        this.transactionId = transactionId;
        this.accountId = accountId;
    }

    // Getters, Setters, equals y hashCode

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionAccountDetailId that = (TransactionAccountDetailId) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, accountId);
    }
}
