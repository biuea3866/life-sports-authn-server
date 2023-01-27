package biuea.lifesports.authnserver.common.filter

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.domain.auth.AuthnService
import biuea.lifesports.authnserver.domain.user.constants.APIType
import biuea.lifesports.authnserver.domain.user.converter.APITypeConverter
import biuea.lifesports.authnserver.domain.auth.error.AuthnErrors
import biuea.lifesports.authnserver.infrastructure.auth.event.TokenProviderEvent
import biuea.lifesports.authnserver.infrastructure.auth.provider.TokenProvider
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationGatewayFilter(val tokenProvider: TokenProvider): AbstractGatewayFilterFactory<AuthenticationGatewayFilter.Config>() {
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
//            val apiType = APITypeConverter().convertToEntityAttribute(
//                dbData = exchange.request.headers["X-Api-Type"]
//                    ?.firstOrNull()
//            )

            val jwtResult = this.tokenProvider.validateAccessToken(event = TokenProviderEvent.ValidateAccessToken(token = token))

            exchange.request
                .mutate()
                .header("X-User-Id", jwtResult.userId.toString())
                .build()

            chain.filter(exchange).then(Mono.fromRunnable { logger.info("uri: {}", exchange.request.uri) })
        }
    }
}