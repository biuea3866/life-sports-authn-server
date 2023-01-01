package biuea.lifesports.authnserver.service.result

import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.service.error.AuthnErrors

class AuthnServiceResult {
    class DecodeToken(
        val userId: Long,
        val email: String,
        val exp: Int,
        val iat: Int
    )
}