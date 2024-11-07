package com.samratalam.orderservice.config.open_api;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPIConfigure() {
        return new OpenAPI()
                .info(new Info()
                        .title("Order Service")
                        .version("1.0")
                        .description("Rest API for Order Service")
                        .license(new License().name("Apache 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("Go to this website")
                        .url("http://localhost:8080/order-service"));
    }
}
