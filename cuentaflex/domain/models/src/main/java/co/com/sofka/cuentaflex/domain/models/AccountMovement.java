package co.com.sofka.cuentaflex.domain.models;

public final class AccountMovement {
    private final Transaction transaction;
    private final AccountRole role;

    public AccountMovement(Transaction transaction, AccountRole role) {
        this.transaction = transaction;
        this.role = role;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public AccountRole getRole() {
        return role;
    }
}
