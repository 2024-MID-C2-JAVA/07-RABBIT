package co.sofka.usecase;



import co.sofka.Account;
import co.sofka.gateway.IAccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaveAccountUseCase implements ISaveAccountService {

    private static final Logger logger = LoggerFactory.getLogger(SaveAccountUseCase.class);

    private final IAccountRepository repository;

    public SaveAccountUseCase(IAccountRepository repository) {
        this.repository = repository;
    }


    public Account save(Account transaction) {

        return repository.save(transaction);
    }


}
