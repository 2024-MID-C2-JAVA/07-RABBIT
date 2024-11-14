package com.bank.management.usecase.appservice;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.Purchase;
import com.bank.management.Transaction;
import com.bank.management.enums.PurchaseType;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.exception.InsufficientFundsException;
import com.bank.management.exception.InvalidPurchaseTypeException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.gateway.MessageSenderGateway;
import com.bank.management.gateway.TransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class ProcessPurchaseWithCardUseCase {

    private final AccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;
    private final CustomerRepository customerRepository;
    private final MessageSenderGateway messageSenderGateway;

    public ProcessPurchaseWithCardUseCase(AccountRepository bankAccountRepository, TransactionRepository transactionRepository, CustomerRepository customerRepository, MessageSenderGateway messageSenderGateway) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
        this.customerRepository = customerRepository;
        this.messageSenderGateway = messageSenderGateway;
    }


    public Optional<Account> apply(Purchase purchase) {
        Optional<Account> accountOptional = bankAccountRepository.findByNumber(purchase.getAccountNumber());

        if (accountOptional.isEmpty()) {
            throw new BankAccountNotFoundException();
        }

        Optional<Customer> customerOptional = customerRepository.findByNumber(purchase.getAccountNumber());

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(purchase.getAccountNumber());
        }

        PurchaseType purchaseType;
        try {
            purchaseType = PurchaseType.valueOf(purchase.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidPurchaseTypeException(purchase.getType().toUpperCase());
        }

        BigDecimal amount = purchase.getAmount();
        BigDecimal fee = calculatePurchaseFee(purchaseType);
        BigDecimal totalCharge = amount.add(fee);

        Account account = accountOptional.get();

        if (account.getAmount().compareTo(totalCharge) < 0) {
            throw new InsufficientFundsException();
        }

        account.adjustBalance(totalCharge.negate());

        Transaction trx = new Transaction.Builder()
                .amountTransaction(purchase.getAmount())
                .transactionCost(fee)
                .typeTransaction(purchaseType.toString())
                .build();
        Optional<Transaction> trxSaved =  transactionRepository.save(trx, account, customerOptional.get(),"BUYER");
        Optional<Account> accountSaved = bankAccountRepository.save(account);
        trxSaved.ifPresent(messageSenderGateway::sendMessage);
        return accountSaved;
    }

    private BigDecimal calculatePurchaseFee(PurchaseType type) {
        return switch (type) {
            case PHYSICAL -> BigDecimal.ZERO; // No fee
            case ONLINE -> new BigDecimal("5.00"); // $5 USD
            default -> throw new RuntimeException(type.toString());
        };
    }
}
