package co.sofka.mapper;

import co.sofka.Customer;
import co.sofka.data.entity.CustomerEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
   @Mapping(source="id", target="id")
   @Mapping(source = "username", target = "username")
   @Mapping(source = "accounts", target = "accounts")

    Customer toCustomer(CustomerEntity model);

    List<Customer> toCustomers(List<CustomerEntity> cardModels);

    @InheritInverseConfiguration
    CustomerEntity toCustomerModel(Customer card);
}
