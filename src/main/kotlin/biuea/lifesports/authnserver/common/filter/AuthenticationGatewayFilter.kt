package biuea.lifesports.authnserver.common.filter

import biuea.lifesports.authnserver.common.design.observer.EventPublisher
import biuea.lifesports.authnserver.common.design.observer.EventSubscriber
import biuea.lifesports.authnserver.common.design.observer.UserIdPublisher
import biuea.lifesports.authnserver.common.design.observer.UserIdSubscriber
import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.AuthnService
import biuea.lifesports.authnserver.service.constants.APIType
import biuea.lifesports.authnserver.service.converter.APITypeConverter
import biuea.lifesports.authnserver.service.error.AuthnErrors
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationGatewayFilter(val authnService: AuthnService): AbstractGatewayFilterFactory<AuthenticationGatewayFilter.Config>() {
    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val logger = LoggerFactory.getLogger(javaClass)

            if (!exchange.request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_AUTHORIZATION))
            }

            val token = exchange.request.headers[HttpHeaders.AUTHORIZATION]
                ?.firstOrNull()
                ?: throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_TOKEN))
            val apiType = APITypeConverter().convertToEntityAttribute(
                dbData = exchange.request.headers["X-Api-Type"]
                    ?.firstOrNull()
            )

            when(apiType) {
                APIType.API -> {
                    val jwtResult = this.authnService.decodeToken(token = token)

                    exchange.request
                        .mutate()
                        .header("X-User-Id", jwtResult.userId.toString())
                        .build()
                }
                APIType.OPEN_API -> {
                    this.authnService.validateOpenAPIKey(token = token)
                }
            }

            chain.filter(exchange).then(Mono.fromRunnable { logger.info("uri: {}", exchange.request.uri) })
        }
    }
}