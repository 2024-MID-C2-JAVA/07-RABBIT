package co.sofka.usecase;




import co.sofka.TransactionAccountDetail;

@FunctionalInterface
public interface ISaveTransactionAccountDetailService {
    TransactionAccountDetail save(TransactionAccountDetail item);


}
