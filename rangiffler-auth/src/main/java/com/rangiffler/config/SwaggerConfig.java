package com.rangiffler.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .schemaRequirement(
                        "rangiffler",
                        new SecurityScheme()
                                .scheme("Bearer")
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER))
                .info(
                        new Info()
                                .title("Rangiffler API Information")
                                .description("API endpoints for the Rangiffler backend"));
    }
}
