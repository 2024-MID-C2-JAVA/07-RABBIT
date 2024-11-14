package com.bank.management.usecase.appservice;

import com.bank.management.Account;
import com.bank.management.Customer;
import com.bank.management.exception.AccountCreationException;
import com.bank.management.exception.CustomerNotFoundException;
import com.bank.management.gateway.AccountRepository;
import com.bank.management.gateway.CustomerRepository;

import java.util.Optional;
import java.util.Random;


public class CreateBankAccountUseCase {

    private final AccountRepository bankAccountRepository;
    private final CustomerRepository customerRepository;

    public CreateBankAccountUseCase(AccountRepository bankAccountRepository, CustomerRepository customerRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.customerRepository = customerRepository;
    }


    public Account apply(Account account, Customer customer) {

        Optional<Customer> customerOptional = customerRepository.findById(customer.getId());
        if (customerOptional.isEmpty()) {
            throw new CustomerNotFoundException(customer.getId());
        }
        Customer customerFound = customerOptional.get();

        Account accountToCreate = new Account.Builder()
                .number(generateAccountNumber())
                .amount(account.getAmount())
                .build();

        accountToCreate.setCustomer(customerFound);

        Optional<Account> accountCreated = bankAccountRepository.save(accountToCreate);

        // Lanzamos una excepción si no se pudo crear la cuenta
        return accountCreated.orElseThrow(() -> new AccountCreationException("Failed to create account for customer: " + customer.getId()));
    }


    private String generateAccountNumber() {

        Random random = new Random();
        StringBuilder accountNumber = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            accountNumber.append(random.nextInt(10));
        }
        return accountNumber.toString();
    }
}
