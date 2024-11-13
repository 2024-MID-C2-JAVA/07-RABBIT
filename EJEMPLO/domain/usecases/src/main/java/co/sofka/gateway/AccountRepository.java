package co.sofka.gateway;

import co.sofka.Account;

public interface AccountRepository {
    Account findById(String id);
    Account save(Account account);
}
