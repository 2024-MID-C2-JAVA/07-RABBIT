package co.sofka;


import java.math.BigDecimal;
import java.time.LocalDate;

public class Account {

    private String id;

    private String number;

    private BigDecimal amount;

    private Customer customer;

    private LocalDate createdAt;

    private Boolean isDeleted;

    public Account() {
    }

    public Account(String id, String number, BigDecimal amount, Customer customer, LocalDate createdAt, Boolean isDeleted) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.customer = customer;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
