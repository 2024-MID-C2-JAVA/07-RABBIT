package co.com.sofka.core.security.authentication.ports;

public interface IdentityProviderPort {
    String getUserIdentity(String username);
}
