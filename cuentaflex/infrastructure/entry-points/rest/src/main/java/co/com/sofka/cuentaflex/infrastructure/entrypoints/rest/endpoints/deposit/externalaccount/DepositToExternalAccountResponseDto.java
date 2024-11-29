package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.deposit.externalaccount;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class DepositToExternalAccountResponseDto {
    private String transactionId;
    private BigDecimal amount;
    private BigDecimal cost;
    private LocalDateTime timestamp;
    private String encryptedPayrollAccountNumber;
    private String encryptedSupplierAccountNumber;

    public DepositToExternalAccountResponseDto() {
    }

    public DepositToExternalAccountResponseDto(String transactionId, BigDecimal amount, BigDecimal cost, LocalDateTime timestamp, String encryptedPayrollAccountNumber, String encryptedSupplierAccountNumber) {
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

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getEncryptedPayrollAccountNumber() {
        return encryptedPayrollAccountNumber;
    }

    public void setEncryptedPayrollAccountNumber(String encryptedPayrollAccountNumber) {
        this.encryptedPayrollAccountNumber = encryptedPayrollAccountNumber;
    }

    public String getEncryptedSupplierAccountNumber() {
        return encryptedSupplierAccountNumber;
    }

    public void setEncryptedSupplierAccountNumber(String encryptedSupplierAccountNumber) {
        this.encryptedSupplierAccountNumber = encryptedSupplierAccountNumber;
    }
}
