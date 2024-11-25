package co.sofka.command.dto;


import java.math.BigDecimal;

public class BankTransactionDepositCajero {


    private String accountNumberClient;


    private BigDecimal amount;



    public BankTransactionDepositCajero() {
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
