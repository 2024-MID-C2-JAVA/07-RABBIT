package co.sofka.usecase;




import co.sofka.Account;
import co.sofka.Customer;

@FunctionalInterface
public interface IGetAccountByNumberService {
    Account findByNumber(String number);

}
