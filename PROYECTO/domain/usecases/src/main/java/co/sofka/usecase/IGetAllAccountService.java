package co.sofka.usecase;




import co.sofka.Account;
import co.sofka.Customer;

import java.util.List;

@FunctionalInterface
public interface IGetAllAccountService {
    List<Account> getAll();


}
