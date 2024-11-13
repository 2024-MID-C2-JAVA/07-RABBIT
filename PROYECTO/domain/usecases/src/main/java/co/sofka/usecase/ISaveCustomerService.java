package co.sofka.usecase;




import co.sofka.Account;
import co.sofka.Customer;

@FunctionalInterface
public interface ISaveCustomerService {
    Customer save(Customer item);


}
