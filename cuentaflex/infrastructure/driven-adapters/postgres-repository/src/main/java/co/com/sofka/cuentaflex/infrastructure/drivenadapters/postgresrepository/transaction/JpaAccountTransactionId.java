package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction;

import jakarta.persistence.Embeddable;

@Embeddable
public class JpaAccountTransactionId {
    private int accountId;
    private int transactionId;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }
}
