package co.sofka.usecase.appBank;




import co.sofka.TransactionAccountDetail;

@FunctionalInterface
public interface ISaveTransactionAccountDetailService {
    TransactionAccountDetail save(TransactionAccountDetail item);


}
