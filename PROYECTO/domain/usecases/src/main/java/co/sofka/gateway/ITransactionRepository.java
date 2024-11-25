package co.sofka.gateway;


import co.sofka.Customer;
import co.sofka.Transaction;

import java.util.List;

public interface ITransactionRepository {

    Transaction save(Transaction id);

    List<Transaction> getAll();

}
