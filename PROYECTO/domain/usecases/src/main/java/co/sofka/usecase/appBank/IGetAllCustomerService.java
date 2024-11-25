package co.sofka.usecase.appBank;




import co.sofka.Customer;

import java.util.List;

@FunctionalInterface
public interface IGetAllCustomerService {
    List<Customer> getAll();

}
