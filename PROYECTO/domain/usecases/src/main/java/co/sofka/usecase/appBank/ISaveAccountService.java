package co.sofka.usecase.appBank;




import co.sofka.Account;

@FunctionalInterface
public interface ISaveAccountService {
    Account save(Account item);


}
