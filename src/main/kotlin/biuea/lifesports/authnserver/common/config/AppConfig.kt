package biuea.lifesports.authnserver.common.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.handler.HandlerMappingIntrospector

@Configuration
class AppConfig {
    @Bean(name = ["mvcHandlerMappingIntrospector"])
    fun mvcHandlerMappingIntrospector(): HandlerMappingIntrospector {
        return HandlerMappingIntrospector()
    }
}