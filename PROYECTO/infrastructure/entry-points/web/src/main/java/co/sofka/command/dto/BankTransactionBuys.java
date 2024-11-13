package co.sofka.command.dto;


import java.math.BigDecimal;

public class BankTransactionBuys {


    private String accountNumberClient;

    private BigDecimal amount;


    private int typeBuys;


    public BankTransactionBuys() {
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

    public int getTypeBuys() {
        return typeBuys;
    }

    public void setTypeBuys(int typeBuys) {
        this.typeBuys = typeBuys;
    }


}
