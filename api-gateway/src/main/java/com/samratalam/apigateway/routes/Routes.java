package com.samratalam.apigateway.routes;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import java.net.URI;

import static org.springframework.cloud.gateway.server.mvc.filter.CircuitBreakerFilterFunctions.circuitBreaker;
import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;


@Configuration
public class Routes {

    @Value("${product.service.base-url}")
    private String productServiceBaseUrl;
    @Value("${order.service.base-url}")
    private String orderServiceBaseUrl;
    @Value("${inventory.service.base-url}")
    private String inventoryServiceBaseUrl;

    @Value("${product.service.path-pattern}")
    private String productServicePathPattern;
    @Value("${order.service.path-pattern}")
    private String orderServicePathPattern;
    @Value("${inventory.service.path-pattern}")
    private String inventoryServicePathPattern;

    @Value("${springdoc.api-docs.path}")
    private String apiDocsPathPattern;

    @Bean
    public RouterFunction<ServerResponse> productService() {
        return route("product_service").route(RequestPredicates.path(productServicePathPattern), HandlerFunctions.http(productServiceBaseUrl))
                .filter(circuitBreaker("productServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> productServiceSwagger() {
        return route("product_service_swagger")
                .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), HandlerFunctions.http(productServiceBaseUrl))
                .filter(circuitBreaker("productSwaggerServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath(apiDocsPathPattern))
                .build();

        /*
                "http:localhost:9091/api/v1/product/aggregate/product-service/v3/api-docs
        --> filter change it to
                "http:localhost:9091/api/v1/product/api-docs
         */
    }

    @Bean
    public RouterFunction<ServerResponse> orderService() {
        return route("order_service")
                .route(RequestPredicates.path(orderServicePathPattern), HandlerFunctions.http(orderServiceBaseUrl))
                .filter(circuitBreaker("orderServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderServiceSwagger() {
        return route("order_service_swagger")
                .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), HandlerFunctions.http(orderServiceBaseUrl))
                .filter(circuitBreaker("orderSwaggerServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath(apiDocsPathPattern)).build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryService() {
        return route("inventory_service")
                .route(RequestPredicates.path(inventoryServicePathPattern), HandlerFunctions.http(inventoryServiceBaseUrl))
                .filter(circuitBreaker("inventoryServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> inventoryServiceSwagger() {
        return route("inventory_service_swagger")
                .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), HandlerFunctions.http(inventoryServiceBaseUrl))
                .filter(circuitBreaker("inventorySwaggerServiceCircuitBreaker", URI.create("forward:/fallbackRoute")))
                .filter(setPath(apiDocsPathPattern)).build();
    }


    @Bean
    public RouterFunction<ServerResponse> fallBackRouter() {
        return route("fallBackRouter")
                .GET("/fallBack", request -> ServerResponse.status(HttpStatus.SERVICE_UNAVAILABLE).body("Service Unavailable. Please try again later."))
                .build();
    }

}
