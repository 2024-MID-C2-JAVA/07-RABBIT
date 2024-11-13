package co.sofka.gateway;


import co.sofka.TransactionAccountDetail;

import java.util.List;

public interface ITransactionAccountDetailRepository {

    TransactionAccountDetail save(TransactionAccountDetail id);

    List<TransactionAccountDetail> getAll();

}
