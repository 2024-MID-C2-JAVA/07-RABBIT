package co.sofka.usecase.appBank;




import co.sofka.Account;

@FunctionalInterface
public interface IGetAccountByNumberService {
    Account findByNumber(String number);

}
