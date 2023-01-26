package biuea.lifesports.authnserver.common.filter

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.AuthnService
import biuea.lifesports.authnserver.service.constants.APIType
import biuea.lifesports.authnserver.service.converter.APITypeConverter
import biuea.lifesports.authnserver.service.error.AuthnErrors
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class AuthenticationGatewayFilter(val authnService: AuthnService): AbstractGatewayFilterFactory<AuthenticationGatewayFilter.Config>() {
    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val logger = LoggerFactory.getLogger(javaClass)
            val request = exchange.request
            val response = exchange.response

            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_AUTHORIZATION))
            }

            val token = request.headers[HttpHeaders.AUTHORIZATION]
                ?.firstOrNull()
                ?: throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_TOKEN))
            val apiType = APITypeConverter().convertToEntityAttribute(
                dbData = request.headers["X-Api-Type"]
                    ?.firstOrNull()
            )

            when(apiType) {
                APIType.API -> {
                    logger.info("decode before token: {}", token)

                    val jwtResult = this.authnService.decodeToken(token = token)

                    logger.info("token: {}", token)

                    addAuthorizationAPIHeaders(
                        response = response,
                        userId = jwtResult.userId
                    )
                }
                APIType.OPEN_API -> {
                    this.authnService.validateOpenAPIKey(token = token)

                    addAuthorizationOpenAPIHeaders(
                        response = response,
                        token = token
                    )
                }
            }

            chain.filter(exchange).then(Mono.fromRunnable { logger.info("uri: {}", exchange.request.uri) })
        }
    }

    private fun addAuthorizationAPIHeaders(
        response: ServerHttpResponse,
        userId: Long
    ) {
        response.headers.set(
            "X-User-Id",
            userId.toString()
        )
    }

    private fun addAuthorizationOpenAPIHeaders(
        response: ServerHttpResponse,
        token: String
    ) {
        response.headers.set(
            "X-OpenAPI",
            token
        )
    }
}