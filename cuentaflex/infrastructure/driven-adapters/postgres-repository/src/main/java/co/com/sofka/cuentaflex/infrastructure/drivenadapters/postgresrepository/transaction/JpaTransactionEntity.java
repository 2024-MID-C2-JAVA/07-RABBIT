package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.transaction;

import co.com.sofka.cuentaflex.domain.models.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "transaction")
@Table(name = "transaction")
public class JpaTransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="amount")
    private BigDecimal amount;

    @Column(name="cost")
    private BigDecimal cost;

    @Temporal(TemporalType.TIMESTAMP)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name="timestamp")
    private LocalDateTime timestamp;

    @OneToMany(mappedBy = "transaction", fetch = FetchType.EAGER)
    private List<JpaAccountTransactionEntity> accountTransactionEntities = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public List<JpaAccountTransactionEntity> getAccountTransactionEntities() {
        return accountTransactionEntities;
    }

    public void setAccountTransactionEntities(List<JpaAccountTransactionEntity> accountTransactionEntities) {
        this.accountTransactionEntities = accountTransactionEntities;
    }

    public void addAccountTransactionEntity(JpaAccountTransactionEntity accountTransactionEntity) {
        accountTransactionEntities.add(accountTransactionEntity);
        accountTransactionEntity.setTransaction(this);
    }
}
