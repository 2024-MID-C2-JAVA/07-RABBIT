package co.sofka.mapper;

import co.sofka.Account;
import co.sofka.data.entity.AccountEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(source="id", target="id")
    @Mapping(source="number", target="number")
    @Mapping(source="amount", target="amount")

    Account toAccount(AccountEntity model);

    List<Account> toAccounts(List<AccountEntity> cardModels);

    @InheritInverseConfiguration
    AccountEntity toAccountModel(Account card);
}
