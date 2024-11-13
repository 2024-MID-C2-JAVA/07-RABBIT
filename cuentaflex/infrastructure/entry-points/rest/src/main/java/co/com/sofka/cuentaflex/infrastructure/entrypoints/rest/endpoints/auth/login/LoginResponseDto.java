package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.login;

public final class LoginResponseDto {
    private String token;

    public LoginResponseDto() {
    }

    public LoginResponseDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
