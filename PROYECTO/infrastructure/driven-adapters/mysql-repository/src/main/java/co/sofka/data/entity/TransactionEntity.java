package co.sofka.data.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount_transaction")
    private BigDecimal amountTransaction;

    @Column(name = "transaction_cost")
    private BigDecimal transactionCost;

    @Column(name = "type_transaction")
    private String typeTransaction;

    @Column(name = "time_stamp")
    private LocalDateTime timeStamp;

    @OneToMany(mappedBy = "transaction", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionAccountDetailEntity> transactionDetails;

}

