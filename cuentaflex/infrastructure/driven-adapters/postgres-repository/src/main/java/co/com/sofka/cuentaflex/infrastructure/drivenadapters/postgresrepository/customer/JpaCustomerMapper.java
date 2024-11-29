package co.com.sofka.cuentaflex.infrastructure.drivenadapters.postgresrepository.customer;

import co.com.sofka.cuentaflex.domain.models.Customer;

public final class JpaCustomerMapper {
    public static Customer fromJpaToModelCustomer(JpaCustomerEntity entity) {
        return new Customer(
                entity.getId() + "",
                entity.getCreatedAt(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getIdentification(),
                entity.isDeleted()
        );
    }

    public static JpaCustomerEntity fromModelToJpaCustomer(Customer model) {
        JpaCustomerEntity jpaCustomerEntity = new JpaCustomerEntity();

        int id = model.getId() == null ? 0 : Integer.parseInt(model.getId());

        jpaCustomerEntity.setId(id);
        jpaCustomerEntity.setCreatedAt(model.getCreatedAt());
        jpaCustomerEntity.setFirstName(model.getFirstName());
        jpaCustomerEntity.setLastName(model.getLastName());
        jpaCustomerEntity.setIdentification(model.getIdentification());
        jpaCustomerEntity.setDeleted(model.isDeleted());

        return jpaCustomerEntity;
    }
}
