package biuea.lifesports.authnserver.infrastructure.openapi

import biuea.lifesports.authnserver.domain.user.entity.OpenAPIKey
import org.springframework.data.jpa.repository.JpaRepository

interface OpenAPIKeyRepository: JpaRepository<OpenAPIKey, Long> {
    fun existsByToken(token: String): Boolean
}