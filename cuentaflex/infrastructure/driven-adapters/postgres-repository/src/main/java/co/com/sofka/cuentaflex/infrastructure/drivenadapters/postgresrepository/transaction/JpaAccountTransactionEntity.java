package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction;

import co.com.sofka.cuentaflex.domain.models.AccountRole;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.account.JpaAccountEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "account_transaction")
public class JpaAccountTransactionEntity {
    @EmbeddedId
    private JpaAccountTransactionId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("accountId")
    @JoinColumn(name = "account_id")
    private JpaAccountEntity account;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("transactionId")
    @JoinColumn(name = "transaction_id")
    private JpaTransactionEntity transaction;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private AccountRole role;

    public JpaAccountTransactionId getId() {
        return id;
    }

    public void setId(JpaAccountTransactionId id) {
        this.id = id;
    }

    public JpaAccountEntity getAccount() {
        return account;
    }

    public void setAccount(JpaAccountEntity account) {
        this.account = account;
    }

    public JpaTransactionEntity getTransaction() {
        return transaction;
    }

    public void setTransaction(JpaTransactionEntity transaction) {
        this.transaction = transaction;
    }

    public AccountRole getRole() {
        return role;
    }

    public void setRole(AccountRole role) {
        this.role = role;
    }
}
