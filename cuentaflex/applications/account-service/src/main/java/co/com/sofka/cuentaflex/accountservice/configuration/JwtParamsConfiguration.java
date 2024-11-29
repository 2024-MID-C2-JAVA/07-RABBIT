package co.com.sofka.cuentaflex.accountservice.configuration;

import co.com.sofka.core.security.jwt.JwtParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class JwtParamsConfiguration {
    @Bean
    public JwtParams jwtParams(
            @Value("${cuentaflex.jwt.secret-key}") String secretKey,
            @Value("${cuentaflex.jwt.expires-in-hours}") int expirationInHours
    ) {
        return new JwtParams(secretKey, expirationInHours);
    }
}
