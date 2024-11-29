package co.com.sofka.core.security.jwt;

public final class JwtParams {
    private final String secretKey;
    private final int expirationInHours;

    public JwtParams(String secretKey, int expirationInHours) {
        this.secretKey = secretKey;
        this.expirationInHours = expirationInHours;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public int getExpirationInHours() {
        return expirationInHours;
    }
}
