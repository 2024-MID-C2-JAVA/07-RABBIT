package co.sofka.data.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class TransactionEntity {

    @Field(name = "id")
    private String id;

    @Field(name = "amount_transaction")
    private BigDecimal amountTransaction;

    @Field(name = "transaction_cost")
    private BigDecimal transactionCost;

    @Field(name = "type_transaction")
    private String typeTransaction;

    @Field(name = "time_stamp")
    private LocalDate timeStamp;


}

