package com.bank.management.usecase.appservice;

import com.bank.management.*;
import com.bank.management.exception.BankAccountNotFoundException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.exception.InsufficientFundsException;
import com.bank.management.exception.InvalidAmountException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;
import com.bank.management.gateway.MessageSenderGateway;
import com.bank.management.gateway.TransactionRepository;

import java.math.BigDecimal;
import java.util.Optional;

public class ProcessWithdrawUseCase {


    private final AccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;
    private final MessageSenderGateway messageSenderGateway;

    public ProcessWithdrawUseCase(AccountRepository bankAccountRepository, CustomerRepository customerRepository, TransactionRepository transactionRepository, MessageSenderGateway messageSenderGateway) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
        this.transactionRepository = transactionRepository;
        this.messageSenderGateway = messageSenderGateway;
    }

    public Optional<Account> apply(Withdrawal withdrawal) {
        if (withdrawal.getAmount() == null || withdrawal.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException();
        }

        Optional<Account> accountOptional = bankAccountRepository.findByNumber(withdrawal.getAccountNumber());
        Optional<Customer> customerOptional = customerRepository.findByUsername(withdrawal.getUsername());
        if (accountOptional.isEmpty()) {
            throw new BankAccountNotFoundException();
        }

        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(withdrawal.getUsername().toString());
        }

        BigDecimal transactionFee = new BigDecimal("1.00");
        BigDecimal totalCharge = withdrawal.getAmount().add(transactionFee);

        Account account = accountOptional.get();
        Customer customer = customerOptional.get();

        if (account.getAmount().compareTo(totalCharge) < 0) {
            throw new InsufficientFundsException();
        }

        account.adjustBalance(totalCharge.negate());
        Transaction trx = new Transaction.Builder()
                .amountTransaction(withdrawal.getAmount())
                .transactionCost(transactionFee)
                .typeTransaction("WITHDRAWAL")
                .build();

        Optional<Transaction> trxSaved = transactionRepository.save(trx, account, customer ,"RECEIVED");
        Optional<Account> accountSaved = bankAccountRepository.save(account);
        trxSaved.ifPresent(messageSenderGateway::sendMessage);
        return accountSaved;
    }

}
