package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.customer.createaccount;

import java.math.BigDecimal;

public final class CreateCustomerAccountRequestDto {
    private String customerId;
    private BigDecimal amount;

    public CreateCustomerAccountRequestDto() {
    }

    public CreateCustomerAccountRequestDto(String customerId, BigDecimal amount) {
        this.customerId = customerId;
        this.amount = amount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
