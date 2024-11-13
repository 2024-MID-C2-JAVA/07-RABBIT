package co.com.sofka.cuentaflex.domain.usecases.customer.getaccount;

public final class GetCustomerAccountRequest {
    private final String customerId;
    private final String accountId;
    private final String initializationVector;
    private final String secretKey;

    public GetCustomerAccountRequest(String customerId, String accountId, String initializationVector, String secretKey) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.initializationVector = initializationVector;
        this.secretKey = secretKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getInitializationVector() {
        return initializationVector;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
