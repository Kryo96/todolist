package progetto.react.com.counter.configurations;

import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.security.SecurityRequirement;

@Configuration
public class SwaggerConfig {

    @Bean
    public OperationCustomizer customize() {
        return (operation, handlerMethod) -> {
            SecurityRequirement securityItem = new SecurityRequirement().addList("bearerAuth");
            operation.addSecurityItem(securityItem);
            return operation;
        };
    }
}