package co.sofka.usecase.appBank;




import co.sofka.Transaction;

@FunctionalInterface
public interface ISaveTransactionService {
    Transaction save(Transaction transaction);


}
