package co.com.sofka.cuentaflex.accountservice.configuration;

import co.com.sofka.cuentaflex.domain.usecases.common.transactions.FeesValues;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Configuration
public class FeesConfiguration {
    @Bean
    public FeesValues feesValues(
            @Value("${cuentaflex.fees.branch-deposit}") BigDecimal branchDeposit,
            @Value("${cuentaflex.fees.atm-deposit}") BigDecimal atmDeposit,
            @Value("${cuentaflex.fees.external-account-deposit}") BigDecimal externalAccountDeposit,
            @Value("${cuentaflex.fees.online-purchase}") BigDecimal onlinePurchase,
            @Value("${cuentaflex.fees.physical-purchase}") BigDecimal physicalPurchase,
            @Value("${cuentaflex.fees.atm-withdrawal}") BigDecimal atmWithdrawal
    ) {
        return new FeesValues(
                branchDeposit.abs().setScale(2, RoundingMode.HALF_UP),
                atmDeposit.abs().setScale(2, RoundingMode.HALF_UP),
                externalAccountDeposit.abs().setScale(2, RoundingMode.HALF_UP),
                onlinePurchase.abs().setScale(2, RoundingMode.HALF_UP),
                physicalPurchase.abs().setScale(2, RoundingMode.HALF_UP),
                atmWithdrawal.abs().setScale(2, RoundingMode.HALF_UP)
        );
    }
}
