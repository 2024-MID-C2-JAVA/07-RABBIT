package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.account;

import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer.JpaCustomerEntity;
import co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction.JpaAccountTransactionEntity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "account")
@Table(name = "account")
public class JpaAccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    private JpaCustomerEntity customer;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<JpaAccountTransactionEntity> accountTransactionEntities;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public JpaCustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(JpaCustomerEntity customer) {
        this.customer = customer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<JpaAccountTransactionEntity> getAccountTransactionEntities() {
        return accountTransactionEntities;
    }

    public void setAccountTransactionEntities(List<JpaAccountTransactionEntity> accountTransactionEntities) {
        this.accountTransactionEntities = accountTransactionEntities;
    }

    public void addAccountTransaction(JpaAccountTransactionEntity accountTransactionEntity) {
        accountTransactionEntity.setAccount(this);
        accountTransactionEntities.add(accountTransactionEntity);
    }
}
