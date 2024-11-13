package co.sofka;



import co.sofka.config.JpaAccountRepository;
import co.sofka.data.entity.AccountEntity;
import co.sofka.data.entity.CustomerEntity;
import co.sofka.gateway.IAccountRepository;
import co.sofka.mapper.AccountMapper;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class AccountRepository implements IAccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    private final JpaAccountRepository repository;



    @Transactional
    @Override
    public Account save(Account item) {
        AccountEntity accountEntity = toAccountEntity(item);
        logger.info("AccountEntity Save ID Number: {} {}",accountEntity.getId(), accountEntity.getNumber());
        AccountEntity save = repository.save(accountEntity);
        return toAccount(save);
    }

    @Override
    public Account findByNumber(String accountNumber) {
        AccountEntity byNumber = repository.findByNumber(Long.parseLong(accountNumber));
        return toAccount(byNumber);
    }

    @Override
    public Account findById(String id) {
        Optional<AccountEntity> byId = repository.findById(Long.parseLong(id));
        if(byId.isPresent()){
            AccountEntity accountEntity = byId.get();
            return toAccount(accountEntity);
        }
        return null;
    }

    @Override
    public List<Account> getAll() {
        List<AccountEntity> all = repository.findAll();
        return toAccounts(all);
    }


    public Account toAccount(AccountEntity accountEntity){

        Account account = new Account();
        account.setAmount(accountEntity.getAmount());
        account.setNumber(accountEntity.getNumber().toString());
        CustomerEntity customer = accountEntity.getCustomer();
        Customer customer1 = new Customer();
        customer1.setId(customer.getId().toString());
        customer1.setUsername(customer.getUsername());

        account.setCustomer(customer1);
        account.setId(accountEntity.getId().toString());
        account.setCreatedAt(accountEntity.getCreatedAt());
        account.setDeleted(accountEntity.getIsDeleted());


        return account;
    }

    public AccountEntity toAccountEntity(Account account){

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setAmount(account.getAmount());
        accountEntity.setNumber(Integer.parseInt(account.getNumber()));
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(Long.parseLong(account.getCustomer().getId()));
        accountEntity.setCustomer(customerEntity);
        if (account.getId() != null){
            accountEntity.setId(Long.parseLong(account.getId()));
        }
        accountEntity.setCreatedAt(account.getCreatedAt());
        accountEntity.setIsDeleted(account.getDeleted());

        return accountEntity;
    }


    public List<Account> toAccounts(List<AccountEntity> accountEntities){
        return accountEntities.stream().map(this::toAccount).toList();
    }


}
