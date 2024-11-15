package co.sofka.security.configuration;


import co.sofka.security.configuration.jwt.JwtRequestFilter;
import co.sofka.security.configuration.jwt.jwtCustomAccessDenaiedHandler;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);


    private JwtRequestFilter jwtRequestFilter;

    jwtCustomAccessDenaiedHandler jwtCustomAccessDenaiedHandler;



    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
    {

        logger.info("Configuracion inicial");

        return http
                .cors(cors -> cors.configurationSource(request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(Arrays.asList("*"));
                    configuration.setAllowedMethods(Arrays.asList("*"));
                    configuration.setAllowedHeaders(Arrays.asList("*"));
                    return configuration;
                }))
                .csrf(csrf ->
                        csrf
                                .disable())
                .authorizeHttpRequests(authRequest ->
                        authRequest
                                .requestMatchers("/v3/api-docs/**","/v3/api-docs.yaml",
                                        "/swagger-ui/**", "/swagger-ui.html", "/swagger-ui/index.html")
                                .permitAll()
                                .requestMatchers
                                        ("/v3/api-docs/**",
                                                "/configuration/ui",
                                                "/swagger-resources/**",
                                                "/swagger-ui.html",
                                                "/webjars/**").permitAll()
                                .requestMatchers("/utils/generate").permitAll()
                                .requestMatchers("/log/**").hasAuthority("ADMIN")
                                .anyRequest().authenticated()
                )
                .sessionManagement(sessionManager->
                        sessionManager
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(e-> e.accessDeniedHandler(jwtCustomAccessDenaiedHandler)
                        .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .build();


    }

}