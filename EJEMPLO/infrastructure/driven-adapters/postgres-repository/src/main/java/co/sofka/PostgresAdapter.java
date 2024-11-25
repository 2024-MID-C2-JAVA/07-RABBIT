package co.sofka;

import co.sofka.config.PostgresRepository;
import co.sofka.data.AccountEntity;
import co.sofka.gateway.AccountRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PostgresAdapter implements AccountRepository {

    private final PostgresRepository repository;

    public PostgresAdapter(PostgresRepository repository) {
        this.repository = repository;
    }

    @Override
    public Account findById(String id) {

        AccountEntity foundAccount = repository.findById(Integer.parseInt(id)).get();

        Account account = new Account(
                foundAccount.getId().toString(),
                foundAccount.getAmount(),
                foundAccount.getCustomer()
        );

        return account;
    }

    @Override
    public Account save(Account account) {
        return null;
    }
}
