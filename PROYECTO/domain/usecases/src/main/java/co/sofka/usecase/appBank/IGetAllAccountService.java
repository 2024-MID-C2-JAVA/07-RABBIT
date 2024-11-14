package co.sofka.usecase.appBank;




import co.sofka.Account;

import java.util.List;

@FunctionalInterface
public interface IGetAllAccountService {
    List<Account> getAll();


}
