package co.com.sofka.cuentaflex.domain.usecases.deposit.externalaccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class DepositToExternalAccountResponse {
    private final String transactionId;
    private final BigDecimal amount;
    private final BigDecimal cost;
    private final LocalDateTime timestamp;
    private final String encryptedPayrollAccountNumber;
    private final String encryptedSupplierAccountNumber;

    public DepositToExternalAccountResponse(
            String transactionId,
            BigDecimal amount,
            BigDecimal cost,
            LocalDateTime timestamp,
            String encryptedPayrollAccountNumber,
            String encryptedSupplierAccountNumber
    ) {
        this.transactionId = transactionId;
        this.amount = amount;
        this.cost = cost;
        this.timestamp = timestamp;
        this.encryptedPayrollAccountNumber = encryptedPayrollAccountNumber;
        this.encryptedSupplierAccountNumber = encryptedSupplierAccountNumber;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getEncryptedPayrollAccountNumber() {
        return encryptedPayrollAccountNumber;
    }

    public String getEncryptedSupplierAccountNumber() {
        return encryptedSupplierAccountNumber;
    }
}
