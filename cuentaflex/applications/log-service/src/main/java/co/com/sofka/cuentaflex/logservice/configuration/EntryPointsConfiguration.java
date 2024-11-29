package co.com.sofka.cuentaflex.logservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "co.com.sofka.cuentaflex.infrastructure.entrypoints.mqlistener")
public class EntryPointsConfiguration {
}
