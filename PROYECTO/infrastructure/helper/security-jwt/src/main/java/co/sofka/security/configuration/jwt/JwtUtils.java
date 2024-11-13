package co.sofka.security.configuration.jwt;

import co.sofka.security.configuration.entity.LoginPartnerRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationDateInMs}")
    private int jwtExpirationMs;


    @Value("${general.config.constans.authorities}")
    public String AUTHORITIES ;

    @Value("${general.config.constans.id}")
    public String ID = "APIJWT";


    @Value("${general.config.constans.headersJWTPartnerLoginID}")
    public String headersJWTLoginID;

    @Value("${general.config.constans.headersJWTPartnerLoginDevice}")
    public String headersJWTLoginDevice;

    @Value("${general.config.constans.headersJWTPartnerLoginType}")
    public String headersJWTLoginType;

    public String generateJwtToken(LoginPartnerRequest data) {

        Map<String, Object> headers = new HashMap<>();
        headers.put(headersJWTLoginID, data.getUsername());
        headers.put(headersJWTLoginDevice, data.getRol());
        headers.put(headersJWTLoginType, "JWT");

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils.createAuthorityList(data.getPermisos());

        String token = Jwts.builder().setId(ID).setSubject(data.getUsername())
                .setHeaderParams(headers)
                .claim(AUTHORITIES,grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public String encryptionPassword(String passwordActual) {

        PasswordEncoder bCryptPasswordEncoder;
        bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(passwordActual);
    }


    public Boolean matchesPasswd(String passwordActual, String passwordBD) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(passwordActual, passwordBD);
    }


}
