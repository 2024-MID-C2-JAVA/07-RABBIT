package co.sofka.security.configuration.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwsHeader;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtil  {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${jwt.secret}")
	private String secret;

	@Value("${general.config.constans.authorities}")
	public String AUTHORITIES ;

	@Value("${general.config.constans.headersJWTPartnerLoginID}")
	public String headersJWTPartnerLoginID;

	@Value("${general.config.constans.headersJWTPartnerLoginDevice}")
	public String headersJWTPartnerLoginDevice;

	@Value("${general.config.constans.headersJWTPartnerLoginType}")
	public String headersJWTPartnerLoginType;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public List getAuthoritiesUsernameFromToken(String token) {
		@SuppressWarnings("unchecked")
		List<String> authorities = (List) getAllClaimsFromToken(token).get(AUTHORITIES);
		authorities.stream().map(item ->{
			logger.info("Authorities: {}", item);
			return item;
		}).collect(Collectors.toList());
		return authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	public JwsHeader getAllHeadersFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getHeader();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

	}
}