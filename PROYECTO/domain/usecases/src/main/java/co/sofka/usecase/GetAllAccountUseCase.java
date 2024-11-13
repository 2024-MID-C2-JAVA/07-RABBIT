package co.sofka.usecase;



import co.sofka.Account;
import co.sofka.Customer;
import co.sofka.gateway.IAccountRepository;
import co.sofka.gateway.ICustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GetAllAccountUseCase implements IGetAllAccountService {

    private static final Logger logger = LoggerFactory.getLogger(GetAllAccountUseCase.class);

    private final IAccountRepository repository;

    public GetAllAccountUseCase(IAccountRepository repository) {
        this.repository = repository;
    }


    public List<Account> getAll() {
        return repository.getAll();
    }


}
