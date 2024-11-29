package co.com.sofka.cuentaflex.accountservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.cuentaflex.infrastructure.entrypoints")
public class EndpointConfiguration {
}
