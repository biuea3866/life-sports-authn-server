package biuea.lifesports.authnserver.common.config

import biuea.lifesports.authnserver.common.design.observer.EventSubscriber
import biuea.lifesports.authnserver.common.design.observer.UserIdSubscriber
import biuea.lifesports.authnserver.common.filter.AuthenticationGatewayFilter
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.cloud.gateway.route.builder.filters
import org.springframework.cloud.gateway.route.builder.routes
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RoutingConfig(val authenticationGatewayFilter: AuthenticationGatewayFilter) {
    @Bean
    fun gatewayRoutes(builder: RouteLocatorBuilder): RouteLocator =
        builder.routes {
            route {
                path("/users/**")
                uri("lb://USER-SERVER")
                filters {
                    filter(authenticationGatewayFilter.apply(AuthenticationGatewayFilter.Config()))
                }
            }
            route {
                path("/points/**")
                uri("lb://POINT-SERVER")
            }
        }
}