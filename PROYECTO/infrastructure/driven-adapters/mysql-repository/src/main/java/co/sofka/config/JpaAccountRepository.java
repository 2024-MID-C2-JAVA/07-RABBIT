package co.sofka.config;

import co.sofka.data.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<AccountEntity, Long> {

    AccountEntity findByNumber(Long accountNumber);


}
