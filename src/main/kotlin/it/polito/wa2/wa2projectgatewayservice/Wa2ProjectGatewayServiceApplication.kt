package it.polito.wa2.wa2projectgatewayservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.context.annotation.Bean

@SpringBootApplication
@EnableEurekaClient
class Wa2ProjectGatewayServiceApplication {

    @Bean
    fun routes( builder: RouteLocatorBuilder): RouteLocator {
        return builder
            .routes()
            .route("notificationService") {
                it -> it.path(true, "/notificationservice/**")
                        .filters { f -> f.rewritePath("notificationservice", "") }
                        .uri("lb://notificationservice")
            }
            .route("orderService") {
                    it -> it.path(true, "/orderservice/**")
                .filters { f -> f.rewritePath("orderservice", "") }
                .uri("lb://orderservice")
            }
            .route("warehouseService") {
                    it -> it.path(true, "/warehouseservice/**")
                .filters { f -> f.rewritePath("warehouseservice", "") }
                .uri("lb://warehouseservice")
            }
            .route("walletService") {
                    it -> it.path(true, "/walletservice/**")
                .filters { f -> f.rewritePath("walletservice", "") }
                .uri("lb://walletservice")
            }
            .route("catalogService") {
                    it -> it.path(true, "/catalogservice/**")
                .filters { f -> f.rewritePath("catalogservice", "") }
                .uri("lb://catalogservice")
            }
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<Wa2ProjectGatewayServiceApplication>(*args)
}
