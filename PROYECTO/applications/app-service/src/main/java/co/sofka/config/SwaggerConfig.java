package co.sofka.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new Info().title("API Banco Virtual")
                        .description("Pasarela de servicios desde el canal hacia el core del banco Virtual")
                        .version("v1.0.0"))
                .addServersItem(new Server().url("/").description("API Banco Virtual"))
                ;
    }
}
