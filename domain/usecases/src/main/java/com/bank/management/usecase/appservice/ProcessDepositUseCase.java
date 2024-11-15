package com.bank.management.usecase.appservice;

import com.bank.management.*;
import com.bank.management.enums.DepositType;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.exception.InvalidAmountException;
import com.bank.management.exception.InvalidDepositTypeException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.gateway.MessageSenderGateway;
import com.bank.management.gateway.TransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class ProcessDepositUseCase {

    private final AccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final MessageSenderGateway messageSenderGateway;

    public ProcessDepositUseCase(AccountRepository bankAccountRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository, MessageSenderGateway messageSenderGateway) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.messageSenderGateway = messageSenderGateway;
    }

    public Optional<Account> apply(Deposit deposit) {
        if (deposit.getAmount() == null || deposit.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            InvalidAmountException exception = new InvalidAmountException();
            String errorMessage = exception.getMessage();
            messageSenderGateway.sendMessageError(errorMessage);
            throw exception;
        }

        Optional<Account> accountOptional = bankAccountRepository.findByNumber(deposit.getAccountNumber());
        if (accountOptional.isEmpty()) {
            BankAccountNotFoundException exception = new BankAccountNotFoundException();
            String errorMessage = exception.getMessage();
            messageSenderGateway.sendMessageError(errorMessage);
            throw exception;
        }
        Optional<Customer> customerOptional = customerRepository.findByUsername(deposit.getusername());
        if (customerOptional.isEmpty()) {
            CustomerNotFoundException exception = new CustomerNotFoundException(deposit.getusername());
            String errorMessage = exception.getMessage();
            messageSenderGateway.sendMessageError(errorMessage);
            throw exception;
        }

        DepositType depositType;
        try {
            depositType = DepositType.valueOf(deposit.getType().toUpperCase());
        } catch (IllegalArgumentException e) {
            InvalidDepositTypeException exception = new InvalidDepositTypeException(deposit.getusername());
            String errorMessage = exception.getMessage();
            messageSenderGateway.sendMessageError(errorMessage);
            throw exception;
        }

        BigDecimal fee = calculateDepositFee(depositType);

        Account account = accountOptional.get();
        account.adjustBalance(deposit.getAmount().subtract(fee));

        Transaction trx = new Transaction.Builder()
                .amountTransaction(deposit.getAmount())
                .transactionCost(fee)
                .typeTransaction(depositType.toString())
                .build();

        Optional<Transaction> trxSaved = transactionRepository.save(trx, account, customerOptional.get(), "RECEIVED");
        Optional<Account> accountSaved = bankAccountRepository.save(account);
        trxSaved.ifPresent(messageSenderGateway::sendTransactionSuccess);
        return accountSaved;

    }

    private BigDecimal calculateDepositFee(DepositType type) {
        return switch (type) {
            case ATM -> new BigDecimal("2.00"); // 2 USD
            case OTHER_ACCOUNT -> new BigDecimal("1.50"); // 1.5 USD
            case BRANCH -> BigDecimal.ZERO;
            default -> throw new InvalidDepositTypeException(type.toString().toUpperCase());
        };
    }
}
