package co.sofka.usecase.appBank;




import co.sofka.Customer;

@FunctionalInterface
public interface ISaveCustomerService {
    Customer save(Customer item);


}
