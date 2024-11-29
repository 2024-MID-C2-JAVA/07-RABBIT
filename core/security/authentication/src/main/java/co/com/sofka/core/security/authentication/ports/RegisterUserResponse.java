package co.com.sofka.core.security.authentication.ports;

public final class RegisterUserResponse {
    private final String identity;

    public RegisterUserResponse(String identity) {
        this.identity = identity;
    }

    public String getIdentity() {
        return identity;
    }
}
