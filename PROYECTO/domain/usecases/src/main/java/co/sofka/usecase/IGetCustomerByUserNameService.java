package co.sofka.usecase;




import co.sofka.Customer;

import java.util.List;

@FunctionalInterface
public interface IGetCustomerByUserNameService {
    Customer findByUsername(String username);

}
