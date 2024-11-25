package co.sofka.security.configuration.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private String generalPass = "@@##g3n3r4lP4SS$$";

	@Value("${general.config.constans.BEARER}")
	public String BEARER;

	@Value("${general.config.constans.AUTHORIZATION}")
	public String AUTHORIZATION = "Authorization";

	@Value("${general.config.constans.authorities}")
	public String AUTHORITIES ;

	@Value("${general.config.constans.id}")
	public String ID = "bancoAPIJWT";

	@Value("${URL.OpenEndpointsRegex}")
	public String URLFree ;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader(AUTHORIZATION);
		String username = null;
		String jwtToken = null;
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);

		if (!URLFree.contains(requestURI)){
			if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER)) {
				jwtToken = requestTokenHeader.substring(7);
				logger.info("Token resivido es  {}",jwtToken);
				try {
					username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				} catch (IllegalArgumentException e) {
				} catch (Exception e) {
				}
			} else {
				logger.warn("JWT Token does not begin with Bearer String");
			}
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails userDetails = new User(username,generalPass, jwtTokenUtil.getAuthoritiesUsernameFromToken(jwtToken));

				logger.info("Datos del usuario {}, roles {}",userDetails.getUsername(),userDetails.getAuthorities());

				if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
					UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					usernamePasswordAuthenticationToken
							.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				}
			}
		}


		chain.doFilter(request, response);
	}

}