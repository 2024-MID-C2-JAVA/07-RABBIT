package co.com.sofka.core.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public final class JwtService {
    private final JwtParams jwtParams;

    public JwtService(JwtParams jwtParams) {
        this.jwtParams = jwtParams;
    }

    public String generateToken(Map<String, Object> claims) {
        SecretKey key = Keys.hmacShaKeyFor(this.jwtParams.getSecretKey().getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .signWith(key)
                .claims(claims)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (long) this.jwtParams.getExpirationInHours() * 60 * 60 * 1000))
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(this.jwtParams.getSecretKey().getBytes(StandardCharsets.UTF_8));

            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getClaimValue(String token, String claimName) {
        try {
            SecretKey key = Keys.hmacShaKeyFor(this.jwtParams.getSecretKey().getBytes(StandardCharsets.UTF_8));

            return Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload()
                    .get(claimName, String.class);
        } catch (Exception e) {
            return null;
        }

    }
}
