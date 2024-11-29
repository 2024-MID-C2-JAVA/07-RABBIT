package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.deposit.externalaccount;

import java.math.BigDecimal;

public final class DepositToExternalAccountRequestDto {
    private String customerId;
    private String accountId;
    private BigDecimal amount;
    private String encryptedAccountNumberToDeposit;

    public DepositToExternalAccountRequestDto() {
    }

    public DepositToExternalAccountRequestDto(
            String customerId,
            String accountId,
            BigDecimal amount,
            String encryptedAccountNumberToDeposit
    ) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.amount = amount;
        this.encryptedAccountNumberToDeposit = encryptedAccountNumberToDeposit;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getEncryptedAccountNumberToDeposit() {
        return encryptedAccountNumberToDeposit;
    }

    public void setEncryptedAccountNumberToDeposit(String encryptedAccountNumberToDeposit) {
        this.encryptedAccountNumberToDeposit = encryptedAccountNumberToDeposit;
    }
}
