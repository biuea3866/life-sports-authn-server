package biuea.lifesports.authnserver.infrastructure.auth.provider

import biuea.lifesports.authnserver.domain.auth.dto.TokenClaim
import biuea.lifesports.authnserver.infrastructure.auth.event.TokenProviderEvent

interface TokenProvider {
    fun generateAccessToken(event: TokenProviderEvent.GenerateAccessToken): String
    fun generateRefreshToken(event: TokenProviderEvent.GenerateRefreshToken): String
    fun validateAccessToken(event: TokenProviderEvent.ValidateAccessToken): TokenClaim
    fun validateRefreshToken(event: TokenProviderEvent.ValidateRefreshToken): TokenClaim
}