package co.sofka.usecase;



import co.sofka.Account;
import co.sofka.Customer;
import co.sofka.gateway.IAccountRepository;
import co.sofka.gateway.ICustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetCustomerByUserNameUseCase implements IGetCustomerByUserNameService {

    private static final Logger logger = LoggerFactory.getLogger(GetCustomerByUserNameUseCase.class);

    private final ICustomerRepository repository;

    public GetCustomerByUserNameUseCase(ICustomerRepository repository) {
        this.repository = repository;
    }


    @Override
    public Customer findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
