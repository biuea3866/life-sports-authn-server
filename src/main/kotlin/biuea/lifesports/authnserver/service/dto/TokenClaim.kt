package biuea.lifesports.authnserver.service.dto

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.error.AuthnErrors

class TokenClaim(
    val userId: Long?,
    val email: String?,
    val exp: Int?,
    val iat: Int?
) {
    fun isValidToken() {
        if (this.userId == null) {
            throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NULL_AMONG_TOKEN_FIELD))
        }

        if (this.email == null) {
            throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NULL_AMONG_TOKEN_FIELD))
        }

        if (this.exp == null) {
            throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NULL_AMONG_TOKEN_FIELD))
        }

        if (this.iat == null) {
            throw UnauthorizedException(error = AuthnErrors.of(error = AuthnErrors.HAS_NULL_AMONG_TOKEN_FIELD))
        }
    }
}