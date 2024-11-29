package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.customer.getaccount;

public class GetCustomerAccountRequestDto {
    private String customerId;
    private String accountId;

    public GetCustomerAccountRequestDto() {
    }

    public GetCustomerAccountRequestDto(String customerId, String accountId) {
        this.customerId = customerId;
        this.accountId = accountId;
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
}
