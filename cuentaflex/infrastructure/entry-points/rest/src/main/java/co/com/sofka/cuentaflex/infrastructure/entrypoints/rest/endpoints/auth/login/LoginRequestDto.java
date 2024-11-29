package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.login;

public final class LoginRequestDto {
    private String username;
    private String encryptedPassword;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String username, String encryptedPassword) {
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }
}
