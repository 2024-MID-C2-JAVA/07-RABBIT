package co.sofka.command.dto;

import java.math.BigDecimal;

public class BankTransactionDepositTransfer {


    private String accountNumberSender;


    private String accountNumberReceiver;



    private BigDecimal amount;



    public BankTransactionDepositTransfer() {
    }

    public String getAccountNumberSender() {
        return accountNumberSender;
    }

    public void setAccountNumberSender(String accountNumberSender) {
        this.accountNumberSender = accountNumberSender;
    }

    public String getAccountNumberReceiver() {
        return accountNumberReceiver;
    }

    public void setAccountNumberReceiver(String accountNumberReceiver) {
        this.accountNumberReceiver = accountNumberReceiver;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


}
