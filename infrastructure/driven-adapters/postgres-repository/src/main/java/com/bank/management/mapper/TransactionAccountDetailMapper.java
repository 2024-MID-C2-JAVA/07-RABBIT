package com.bank.management.mapper;

import com.bank.management.TransactionAccountDetail;
import com.bank.management.data.TransactionAccountDetailEntity;

public class TransactionAccountDetailMapper {

    public static TransactionAccountDetail toDomain(TransactionAccountDetailEntity entity) {
        return new TransactionAccountDetail.Builder()
                .transaction(TransactionMapper.toDomain(entity.getTransaction()))
                .account(BankAccountMapper.toDomain(entity.getAccount()))
                .role(entity.getRole())
                .build();
    }

    public static TransactionAccountDetailEntity toEntity(TransactionAccountDetail transactionAccountDetail) {
        return new TransactionAccountDetailEntity(
                TransactionMapper.toEntity(transactionAccountDetail.getTransaction()),
                BankAccountMapper.toEntity(transactionAccountDetail.getAccount()), "RECEIVED");

    }

}
