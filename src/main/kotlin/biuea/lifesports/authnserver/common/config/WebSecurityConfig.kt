package biuea.lifesports.authnserver.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
@Configuration
class WebSecurityConfig {
    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http.httpBasic()
            .and()
            .authorizeExchange()
            .pathMatchers("/**")
            .permitAll()
            .and()
            .headers()
            .frameOptions()
            .disable()
            .and()
            .csrf()
            .disable()
            .build()
    }
}