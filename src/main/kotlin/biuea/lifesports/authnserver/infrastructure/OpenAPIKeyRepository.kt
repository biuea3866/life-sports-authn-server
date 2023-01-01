package biuea.lifesports.authnserver.infrastructure

import biuea.lifesports.authnserver.service.entity.OpenAPIKey
import org.springframework.data.jpa.repository.JpaRepository

interface OpenAPIKeyRepository: JpaRepository<OpenAPIKey, Long> {
    fun existsByToken(token: String): Boolean
}