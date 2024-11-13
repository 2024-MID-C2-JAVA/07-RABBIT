package co.sofka.usecase;

import co.sofka.Account;
import co.sofka.gateway.AccountRepository;

public class GetAccountByIdUseCase {

    private final AccountRepository repository;

    public GetAccountByIdUseCase(AccountRepository repository) {
        this.repository = repository;
    }

    public Account apply(String id) {
        return repository.findById(id);
    }

}
