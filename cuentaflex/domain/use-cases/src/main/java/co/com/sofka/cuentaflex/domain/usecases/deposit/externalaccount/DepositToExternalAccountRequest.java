package co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount;

import java.math.BigDecimal;

public final class DepositToExternalAccountRequest {
    private final String customerId;
    private final String accountId;
    private final BigDecimal amount;
    private final String encryptedAccountNumberToDeposit;
    private final String initializationVector;
    private final String secretKey;

    public DepositToExternalAccountRequest(
            String customerId,
            String accountId,
            BigDecimal amount,
            String encryptedAccountNumberToDeposit,
            String initializationVector,
            String secretKey
    ) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.amount = amount;
        this.encryptedAccountNumberToDeposit = encryptedAccountNumberToDeposit;
        this.initializationVector = initializationVector;
        this.secretKey = secretKey;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getEncryptedAccountNumberToDeposit() {
        return encryptedAccountNumberToDeposit;
    }

    public String getInitializationVector() {
        return initializationVector;
    }

    public String getSecretKey() {
        return secretKey;
    }
}
