package co.sofka.usecase;




import co.sofka.Account;
import co.sofka.Transaction;

import java.util.List;

@FunctionalInterface
public interface ISaveTransactionService {
    Transaction save(Transaction transaction);


}
