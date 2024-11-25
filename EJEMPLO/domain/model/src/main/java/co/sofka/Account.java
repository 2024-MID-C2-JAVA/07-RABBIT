package co.sofka;

import java.math.BigDecimal;

public class Account {

    private String id;
    private BigDecimal amount;
    private String customer;

    public Account(String id, BigDecimal amount, String customer) {
        this.id = id;
        this.amount = amount;
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }
}
