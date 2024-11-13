package co.sofka;



import co.sofka.config.JpaBanktransactionRepository;
import co.sofka.data.entity.CustomerEntity;
import co.sofka.gateway.IAccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class AccountRepository implements IAccountRepository {

    private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);

    private final JpaBanktransactionRepository repository;

    private final MongoTemplate mongoTemplate;


    @Override
    public Account save(Account item) {
        List<CustomerEntity> banktransaction = mongoTemplate.findAll(CustomerEntity.class, "banktransaction");
        banktransaction.stream().forEach(customerEntity -> {
            customerEntity.getAccounts().stream().forEach(accountEntity -> {
                if (accountEntity.getNumber().equals(item.getNumber())){
                    accountEntity.setAmount(item.getAmount());
                    mongoTemplate.save(customerEntity);
                }
            });
        });
       return item;
    }

    @Override
    public Account findByNumber(String accountNumber) {
        CustomerEntity item = repository.findByNumberAccount(accountNumber);
        logger.info("Account found: {} {} ", item.getAccounts().size(),item.getUsername());
        if(item.getAccounts() != null){

            List<Account> collect = item.getAccounts().stream().map(accountEntity -> {
                Account account = new Account();
                account.setId(accountEntity.getId());
                account.setNumber(accountEntity.getNumber());
                account.setAmount(accountEntity.getAmount());
                logger.info("Account found: " + accountEntity.getNumber());

                return account;
            }).collect(Collectors.toList());

            return collect.stream().filter(account -> account.getNumber().equals(accountNumber)).findFirst().orElse(null);

        }
        return null;
    }

    @Override
    public Account findById(String id) {

        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }



}
