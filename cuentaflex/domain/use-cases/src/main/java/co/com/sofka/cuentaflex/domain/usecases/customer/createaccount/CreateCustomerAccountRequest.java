package co.com.sofka.cuentaflex.domain.usecases.customer.createaccount;

import java.math.BigDecimal;

public final class CreateCustomerAccountRequest {
    private final String customerId;
    private final BigDecimal amount;
    private final String initializationVector;
    private final String secretKey;

    public CreateCustomerAccountRequest(String customerId, BigDecimal amount, String initializationVector, String secretKey) {
        this.customerId = customerId;
        this.amount = amount;
        this.initializationVector = initializationVector;
        this.secretKey = secretKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getInitializationVector() {
        return initializationVector;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
