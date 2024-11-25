package co.sofka.gateway;


import co.sofka.Account;

import java.util.List;

public interface IAccountRepository {

    Account save(Account item);
    Account findByNumber(String accountNumber);
    Account findById(String id);
    List<Account> getAll();
}
