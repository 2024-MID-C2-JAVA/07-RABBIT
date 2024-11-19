package com.bank.management.data;

import jakarta.persistence.*;

@Entity
@Table(name = "transaction_account_detail")
public class TransactionAccountDetailEntity {

    @EmbeddedId
    private TransactionAccountDetailId id;

    @ManyToOne
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id", nullable = false)
    private TransactionEntity transaction;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "role", nullable = false)
    private String role; // Ej: "SENDER" o "RECEIVER"

    public TransactionAccountDetailEntity() {}

    public TransactionAccountDetailEntity(TransactionEntity transaction, AccountEntity account, String role) {
        this.transaction = transaction;
        this.account = account;
        this.role = role;
        this.id = new TransactionAccountDetailId(transaction.getId(), account.getId());
    }


    public TransactionAccountDetailId getId() {
        return id;
    }

    public void setId(TransactionAccountDetailId id) {
        this.id = id;
    }

    public TransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionEntity transaction) {
        this.transaction = transaction;
    }

    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(AccountEntity account) {
        this.account = account;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
