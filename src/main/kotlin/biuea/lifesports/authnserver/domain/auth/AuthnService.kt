package biuea.lifesports.authnserver.domain.auth

import biuea.lifesports.authnserver.domain.auth.command.AuthnServiceCommand
import biuea.lifesports.authnserver.domain.user.entity.User
import biuea.lifesports.authnserver.domain.auth.result.AuthnServiceResult

interface AuthnService {
    fun login(command: AuthnServiceCommand.Login): String
    fun logout(command: AuthnServiceCommand.Logout)
}