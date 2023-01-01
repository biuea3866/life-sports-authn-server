package biuea.lifesports.authnserver.service

import biuea.lifesports.authnserver.service.entity.User
import biuea.lifesports.authnserver.service.result.AuthnServiceResult

interface AuthnService {
    fun decodeToken(token: String): AuthnServiceResult.DecodeToken
    fun validateUser(
        userId: Long,
        email: String
    ): User
    fun validateOpenAPIKey(token: String)
}