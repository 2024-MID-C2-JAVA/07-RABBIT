package com.bank.management.mapper;

import com.bank.management.Transaction;
import com.bank.management.data.TransactionDocument;

public class TransactionMapper {

    public static TransactionDocument toDocument(Transaction transaction) {
        TransactionDocument document = new TransactionDocument();
        document.setId(transaction.getId());
        document.setAmountTransaction(transaction.getAmountTransaction());
        document.setTransactionCost(transaction.getTransactionCost());
        document.setTypeTransaction(transaction.getTypeTransaction());
        document.setTimeStamp(transaction.getTimeStamp());
        return document;
    }

    public static Transaction toDomain(TransactionDocument document) {
        return new Transaction.Builder()
                .id(document.getId())
                .amountTransaction(document.getAmountTransaction())
                .transactionCost(document.getTransactionCost())
                .typeTransaction(document.getTypeTransaction())
                .timeStamp(document.getTimeStamp())
                .build();
    }
}
