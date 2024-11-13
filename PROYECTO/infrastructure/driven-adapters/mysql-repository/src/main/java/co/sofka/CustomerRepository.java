package co.sofka;



import co.sofka.config.JpaCustomerRepository;
import co.sofka.data.entity.CustomerEntity;
import co.sofka.gateway.ICustomerRepository;
import co.sofka.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CustomerRepository implements ICustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

    private final JpaCustomerRepository repository;

    private final CustomerMapper mapper;


    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public Customer findByUsername(String username) {
        CustomerEntity byUsername = repository.findByUsername(username);
        return toCustomer(byUsername);
    }

    @Override
    public List<Customer> getAll() {
        List<CustomerEntity> all = repository.findAll();

        return toCustomers(all);
    }



    public Customer toCustomer(CustomerEntity customerEntity){
        Customer customer = new Customer();
        customer.setId(customerEntity.getId().toString());
        customer.setUsername(customerEntity.getUsername());
        customer.setPwd(customerEntity.getPwd());

        List<Account> accounts = new ArrayList<>();
        customerEntity.getAccounts().forEach(accountEntity -> {
            logger.info("AccountEntity: "+accountEntity.getNumber());
            Account account = new Account();
            account.setId(accountEntity.getId().toString());
            account.setAmount(accountEntity.getAmount());
            account.setNumber(accountEntity.getNumber().toString());
            account.setCreatedAt(accountEntity.getCreatedAt());
            account.setDeleted(accountEntity.getIsDeleted());
            accounts.add(account);
        });
        logger.info("Accounts: "+accounts.size());
        customer.setAccounts(accounts);
        return customer;
    }

    public List<Customer> toCustomers(List<CustomerEntity> customerEntity){
        List<Customer> customers = new ArrayList<>();
        customers = customerEntity.stream().map(item->{
            logger.info("CustomerEntity: "+item.getUsername());
            Customer customer = toCustomer(item);
            logger.info("Customer: "+customer.getAccounts().size());
            return customer;


        }).collect(Collectors.toList());

        return customers;
    }



}
