package com.bank.management.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public org.springdoc.core.models.GroupedOpenApi authEndpoints() {
        return GroupedOpenApi.builder()
                .group("auth-endpoints")
                .pathsToMatch("/auth/v1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicEndpoints() {
        return GroupedOpenApi.builder()
                .group("public-endpoints")
                .pathsToMatch("/api/v1/public/**")
                .build();
    }

    @Bean
    public org.springdoc.core.models.GroupedOpenApi protectedEndpoints() {
        return GroupedOpenApi.builder()
                .group("protected-endpoints")
                .pathsToMatch("/api/v1/private/transactions/**")
                .addOperationCustomizer((operation, handlerMethod) ->
                        operation.addSecurityItem(new SecurityRequirement().addList("bearer-token"))
                )
                .build();
    }

    @Bean
    public OpenAPI protectedScheme() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-token",
                                new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
