package co.com.sofka.cuentaflex.domain.drivenports.repositories;

import co.com.sofka.cuentaflex.domain.models.Account;

import java.util.List;

public interface AccountRepository {
    Account createAccount(Account account);
    Account getByIdAndCustomerId(String accountId, String customerId);
    Account getByAccountNumber(int number);
    List<Account> updateMany(Account... accounts);
}
