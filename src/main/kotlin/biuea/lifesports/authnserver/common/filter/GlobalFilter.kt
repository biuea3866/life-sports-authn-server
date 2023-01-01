package biuea.lifesports.authnserver.common.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilter: AbstractGatewayFilterFactory<GlobalFilter.Config>() {
    private val logger = LoggerFactory.getLogger(javaClass)

    class Config(
        val message: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    )

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            logger.info("Global Filter base message: {}", config.message)

            if (config.preLogger) {
                logger.info("Global Filter Start: request id -> {}", exchange.request.id)
            }

            chain.filter(exchange).then(
                Mono.fromRunnable {
                    if (config.postLogger) {
                        logger.info("Global Filter End: response id -> {}", exchange.response.statusCode)
                    }
                }
            )
        }
    }
}