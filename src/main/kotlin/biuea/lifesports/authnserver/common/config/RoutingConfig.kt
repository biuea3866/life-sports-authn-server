package biuea.lifesports.authnserver.common.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RoutingConfig {
    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator =
        builder.routes {
            route {
                path("/user-server/**")
                filters { addRequestHeader("", "") }
                uri("http://localhost:8081")
            }
        }
}