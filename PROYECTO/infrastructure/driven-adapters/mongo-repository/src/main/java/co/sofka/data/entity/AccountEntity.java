package co.sofka.data.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class AccountEntity {


    @Field(name = "id")
    private String id;

    @Field(name = "number")
    private String number;

    @Field(name = "amount")
    private BigDecimal amount;

    @Field(name = "created_at")
    private LocalDate createdAt;

    @Field(name = "is_deleted")
    private Boolean isDeleted;

    private List<TransactionAccountDetailEntity> transactionAccountDetailEntity;


}

