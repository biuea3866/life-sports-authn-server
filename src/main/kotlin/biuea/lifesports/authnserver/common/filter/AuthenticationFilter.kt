package biuea.lifesports.authnserver.common.filter

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.AuthnService
import biuea.lifesports.authnserver.service.constants.APIType
import biuea.lifesports.authnserver.service.error.AuthnErrors
import biuea.lifesports.authnserver.service.result.AuthnServiceResult
import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest

class AuthenticationFilter(val authnService: AuthnService): AbstractGatewayFilterFactory<AuthenticationFilter.Config>() {
    private val logger = LoggerFactory.getLogger(javaClass)

    class Config

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val request = exchange.request

            if (!request.headers.containsKey(HttpHeaders.AUTHORIZATION)) {
                throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_AUTHORIZATION))
            }

            val token = request.headers[HttpHeaders.AUTHORIZATION]
                ?.firstOrNull()
                ?: throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_TOKEN))
            val apiType = request.headers["X-Api-Type"]
                ?.firstOrNull() as APIType?
                ?: throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NOT_TOKEN))

            when(apiType) {
                APIType.API -> {
                    val jwtResult = this.authnService.decodeToken(token = token)

                    logger.info("token: {}", token)

                    addAuthorizationAPIHeaders(
                        request = request,
                        userId = jwtResult.userId
                    )
                }
                APIType.OPEN_API -> {
                    this.authnService.validateOpenAPIKey(token = token)

                    addAuthorizationOpenAPIHeaders(
                        request = request,
                        token = token
                    )
                }
            }

            chain.filter(exchange)
        }
    }

    private fun addAuthorizationAPIHeaders(
        request: ServerHttpRequest,
        userId: Long
    ) {
        request.headers.set(
            "X-User-Id",
            userId.toString()
        )
    }

    private fun addAuthorizationOpenAPIHeaders(
        request: ServerHttpRequest,
        token: String
    ) {
        request.headers.set(
            "X-OpenAPI",
            token
        )
    }
}