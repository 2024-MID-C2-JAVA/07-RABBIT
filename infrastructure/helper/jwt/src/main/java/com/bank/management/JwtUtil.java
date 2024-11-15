    package com.bank.management;

    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.io.Decoders;
    import io.jsonwebtoken.security.Keys;
    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.stereotype.Service;

    import javax.crypto.SecretKey;
    import java.util.Collection;
    import java.util.Date;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.function.Function;
    import java.util.stream.Collectors;

    @Service
    public class JwtUtil {

        @Value("${jwt.secret}")
        private String secretKey;

        public String generateToken(UserDetails userDetails) {
            return generateToken(userDetails, new HashMap<>());
        }

        public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims) {
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

            extraClaims.put("roles", authorities.stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));

            return Jwts.builder()
                    .claims(extraClaims)
                    .subject(userDetails.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
                    .signWith(getSignIngKey())
                    .compact();
        }
        public Claims extractAllClaims(String token) {
            return Jwts.parser()
                    .verifyWith(getSignIngKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        }

        private Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        public Boolean isTokenExpired(String token) {
            return extractExpiration(token).before(new Date());
        }

        public Boolean isTokenValid(String token, UserDetails userDetails) {
            final String username = extractUsername(token);
            return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }


        private SecretKey getSignIngKey() {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            return Keys.hmacShaKeyFor(keyBytes);
        }


    }

