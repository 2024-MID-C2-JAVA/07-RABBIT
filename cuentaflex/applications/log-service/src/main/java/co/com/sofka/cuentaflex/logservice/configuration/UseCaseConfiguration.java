package co.com.sofka.cuentaflex.logservice.configuration;

import co.com.sofka.shared.domain.usecases.UseCase;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "co.com.sofka.cuentaflex.domain.usecases.logservice",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = UseCase.class)
        }
)
public class UseCaseConfiguration {
}
