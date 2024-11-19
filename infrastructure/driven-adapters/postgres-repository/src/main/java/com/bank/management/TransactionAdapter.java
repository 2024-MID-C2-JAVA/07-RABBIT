package com.bank.management;

import com.bank.management.config.PostgresTransacionRepository;
import com.bank.management.config.PostgresTransactionAccountDetRepository;
import com.bank.management.data.TransactionAccountDetailEntity;
import com.bank.management.data.TransactionEntity;
import com.bank.management.gateway.TransactionRepository;
import com.bank.management.mapper.BankAccountMapper;
import com.bank.management.mapper.TransactionMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class TransactionAdapter implements TransactionRepository {

    private final PostgresTransacionRepository transactionRepository;
    private final PostgresTransactionAccountDetRepository transactionAccountDetailRepository;

    public TransactionAdapter(PostgresTransacionRepository transactionRepository, PostgresTransactionAccountDetRepository trxAccountDetRepository) {
        this.transactionRepository = transactionRepository;

        this.transactionAccountDetailRepository = trxAccountDetRepository;
    }

    @Override
    public Optional<Transaction> save(Transaction trx, Account account, Customer customer, String role) {
        TransactionEntity trxEntity = TransactionMapper.toEntity(trx);
        TransactionEntity trxSaved = transactionRepository.save(trxEntity);
        TransactionAccountDetailEntity trxDetail = new TransactionAccountDetailEntity(trxSaved, BankAccountMapper.toEntity(account), role);
        transactionAccountDetailRepository.save(trxDetail);
        return Optional.ofNullable(TransactionMapper.toDomain(trxSaved));
    }
}
