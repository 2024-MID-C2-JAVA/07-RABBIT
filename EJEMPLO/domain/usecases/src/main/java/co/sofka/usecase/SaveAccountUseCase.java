package co.sofka.usecase;

import co.sofka.Account;
import co.sofka.gateway.AccountRepository;

public class SaveAccountUseCase {

    private final AccountRepository repository;

    public SaveAccountUseCase(AccountRepository accountRepository) {
        this.repository = accountRepository;
    }

    public Account apply(Account account) {
        return repository.save(account);
    }
}
