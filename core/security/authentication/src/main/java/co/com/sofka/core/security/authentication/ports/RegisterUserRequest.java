package co.com.sofka.core.security.authentication.ports;

public final class RegisterUserRequest {
    private final String firstName;
    private final String lastName;
    private final String encryptedIdentification;
    private final String username;
    private final String encryptedPassword;
    private final String initializationVector;
    private final String symmetricKey;

    public RegisterUserRequest(String firstName, String lastName, String encryptedIdentification, String username, String encryptedPassword, String initializationVector, String symmetricKey) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.encryptedIdentification = encryptedIdentification;
        this.username = username;
        this.encryptedPassword = encryptedPassword;
        this.initializationVector = initializationVector;
        this.symmetricKey = symmetricKey;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEncryptedIdentification() {
        return encryptedIdentification;
    }

    public String getUsername() {
        return username;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public String getInitializationVector() {
        return initializationVector;
    }

    public String getSymmetricKey() {
        return symmetricKey;
    }
}
