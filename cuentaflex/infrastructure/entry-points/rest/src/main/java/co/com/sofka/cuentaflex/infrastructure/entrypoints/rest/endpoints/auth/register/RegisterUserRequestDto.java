package co.com.sofka.cuentaflex.infrastructure.entrypoints.rest.endpoints.auth.register;

public final class RegisterUserRequestDto {
    private String firstName;
    private String lastName;
    private String encryptedIdentification;
    private String username;
    private String encryptedPassword;

    public RegisterUserRequestDto() {
    }

    public RegisterUserRequestDto(String firstName, String lastName, String encryptedIdentification, String username, String encryptedPassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.encryptedIdentification = encryptedIdentification;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEncryptedIdentification() {
        return encryptedIdentification;
    }

    public void setEncryptedIdentification(String encryptedIdentification) {
        this.encryptedIdentification = encryptedIdentification;
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
