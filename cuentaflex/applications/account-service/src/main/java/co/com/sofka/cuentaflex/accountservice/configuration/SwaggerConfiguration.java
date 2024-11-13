package co.com.sofka.cuentaflex.accountservice.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("/api")
                                .description("Default Server")
                ))
                .info(new Info().title("Cuentaflex Account Service").version("1.0.0"))
                .addSecurityItem(new SecurityRequirement().addList("Bearer"))
                .components(
                        new Components().addSecuritySchemes(
                                "Bearer",
                                new SecurityScheme()
                                        .name("Bearer")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                );
    }
}
