package co.sofka.command.dto;


import java.math.BigDecimal;

public class BankTransactionDepositSucursal {

    private String accountNumberClient;


    private BigDecimal amount;

    public BankTransactionDepositSucursal() {
    }

    public String getAccountNumberClient() {
        return accountNumberClient;
    }

    public void setAccountNumberClient(String accountNumberClient) {
        this.accountNumberClient = accountNumberClient;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
