package co.sofka.data.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data

public class TransactionAccountDetailEntity {

    @Field
    private String id;

    @Field(name = "transaction")
    private TransactionEntity transaction;

    @Field(name = "transaction_role")
    private String transactionRole;

}
