package co.sofka.usecase.appBank;




import co.sofka.Customer;

@FunctionalInterface
public interface IGetCustomerByUserNameService {
    Customer findByUsername(String username);

}
