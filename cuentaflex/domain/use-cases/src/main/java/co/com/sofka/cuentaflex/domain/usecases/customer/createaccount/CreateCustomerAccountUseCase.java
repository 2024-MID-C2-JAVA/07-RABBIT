package co.com.sofka.cuentaflex.domain.usecases.customer.createaccount;

import co.com.sofka.core.cryptography.aes.AESCipher;
import co.com.sofka.cuentaflex.domain.drivenports.repositories.AccountRepository;
import co.com.sofka.cuentaflex.domain.drivenports.repositories.CustomerRepository;
import co.com.sofka.cuentaflex.domain.models.Account;
import co.com.sofka.shared.domain.usecases.ResultWith;
import co.com.sofka.shared.domain.usecases.UseCase;

import java.math.BigDecimal;

public final class CreateCustomerAccountUseCase implements UseCase<CreateCustomerAccountRequest, ResultWith<CreateCustomerAccountResponse>> {
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public CreateCustomerAccountUseCase(CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public ResultWith<CreateCustomerAccountResponse> execute(CreateCustomerAccountRequest request) {
        if (request.getAmount().compareTo(BigDecimal.ZERO) < 0) {
            return ResultWith.failure(CreateCustomerAccountErrors.NEGATIVE_INITIAL_AMOUNT);
        }

        boolean customerExists = this.customerRepository.existsCustomer(request.getCustomerId());

        if (!customerExists) {
            return ResultWith.failure(CreateCustomerAccountErrors.CUSTOMER_NOT_FOUND);
        }

        Account account = new Account(
                null,
                0,
                request.getAmount(),
                request.getCustomerId()
        );

        Account addedAccount = this.accountRepository.createAccount(account);

        return ResultWith.success(new CreateCustomerAccountResponse(
                addedAccount.getId(),
                AESCipher.encryptToBase64(String.valueOf(addedAccount.getNumber()), request.getSecretKey(), request.getInitializationVector()),
                addedAccount.getAmount()
        ));
    }
}
