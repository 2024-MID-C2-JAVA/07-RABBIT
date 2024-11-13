package co.sofka;



import co.sofka.config.JpaBanktransactionRepository;
import co.sofka.data.entity.CustomerEntity;
import co.sofka.gateway.ICustomerRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@AllArgsConstructor
public class CustomerRepository implements ICustomerRepository {

    private static final Logger logger = LoggerFactory.getLogger(CustomerRepository.class);

//    private final JpaBanktransactionRepository repository;

    private final MongoTemplate mongoTemplate;


    @Override
    public Customer save(Customer customer) {
        if (customer.getAccounts()==null){
            customer.setAccounts(new ArrayList<>());
        }
        mongoTemplate.save(customer, "banktransaction");
        return customer;
    }

    @Override
    public Customer findById(String id) {
        return null;
    }

    @Override
    public Customer findByUsername(String username) {
        List<CustomerEntity> all = mongoTemplate.findAll(CustomerEntity.class, "banktransaction");

        all = all.stream().filter(item->item.getUsername().equals(username)).collect(Collectors.toList());

        if(all.size() == 0){
            return null;
        }
        CustomerEntity item = all.get(0);
        Customer customer = new Customer();
        customer.setId(item.getId().toString());
        customer.setUsername(item.getUsername());
        customer.setPwd(item.getPwd());
        customer.setAccounts(new ArrayList<>());
        if(item.getAccounts() != null){
            customer.setAccounts(item.getAccounts().stream().map(accountEntity -> {
                Account account = new Account();
                account.setId(accountEntity.getId());
                account.setNumber(accountEntity.getNumber());
                account.setAmount(accountEntity.getAmount());
                account.setCustomer(customer);
                return account;
            }).collect(Collectors.toList()));
        }
        return customer;

    }

    @Override
    public List<Customer> getAll() {
//        List<CustomerEntity> all = repository.findAll();
        List<CustomerEntity> all = mongoTemplate.findAll(CustomerEntity.class, "banktransaction");

        List<Customer> customers = new ArrayList<>();

        customers = all.stream().map(item->{
            Customer customer = new Customer();
            customer.setId(item.getId().toString());
            customer.setUsername(item.getUsername());
            customer.setPwd(item.getPwd());
            customer.setAccounts(new ArrayList<>());
            if(item.getAccounts() != null){
                customer.setAccounts(item.getAccounts().stream().map(accountEntity -> {
                    Account account = new Account();
                    account.setId(accountEntity.getId());
                    account.setNumber(accountEntity.getNumber());
                    account.setAmount(accountEntity.getAmount());
                    account.setCustomer(customer);
                    return account;
                }).collect(Collectors.toList()));
            }



            return customer;
        }).collect(Collectors.toList());

        return customers;
    }




}
