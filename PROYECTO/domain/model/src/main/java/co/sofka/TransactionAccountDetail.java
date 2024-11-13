package co.sofka;

public class TransactionAccountDetail {
    private String id;
    private Account account;
    private Transaction transaction;
    private String transactionRole;

    public TransactionAccountDetail(String id, Account account, Transaction transaction, String transactionRole) {
        this.id = id;
        this.account = account;
        this.transaction = transaction;
        this.transactionRole = transactionRole;
    }

    public TransactionAccountDetail() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public String getTransactionRole() {
        return transactionRole;
    }

    public void setTransactionRole(String transactionRole) {
        this.transactionRole = transactionRole;
    }
}
