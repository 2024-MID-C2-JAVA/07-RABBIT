package co.sofka.usecase;




import co.sofka.Account;
import co.sofka.Transaction;

@FunctionalInterface
public interface ISaveAccountService {
    Account save(Account item);


}
