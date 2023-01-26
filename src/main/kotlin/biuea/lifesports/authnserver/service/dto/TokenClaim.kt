package biuea.lifesports.authnserver.service.dto

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.error.AuthnErrors

class TokenClaim(
    val userId: Long?,
    val email: String?,
    val exp: Long?
) {
    fun isValidToken() {
        if (
            this.userId == null ||
            this.email == null ||
            this.exp == null
        ) throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NULL_AMONG_TOKEN_FIELD))
    }
}