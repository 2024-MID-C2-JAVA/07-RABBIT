package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.register;

public final class RegisterUserResponseDto {
    private String customerId;

    public RegisterUserResponseDto() {
    }

    public RegisterUserResponseDto(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
