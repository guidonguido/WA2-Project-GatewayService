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
            .build()
    }
}

fun main(args: Array<String>) {
    runApplication<Wa2ProjectGatewayServiceApplication>(*args)
}
