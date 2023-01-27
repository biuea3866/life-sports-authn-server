package biuea.lifesports.authnserver.domain.auth

import biuea.lifesports.authnserver.domain.auth.command.AuthnServiceCommand
import biuea.lifesports.authnserver.infrastructure.auth.provider.TokenProvider
import biuea.lifesports.authnserver.infrastructure.user.validator.UsersValidator
import org.springframework.stereotype.Service

@Service
class AuthnServiceImpl(
    val tokenProvider: TokenProvider,
    val usersValidator: UsersValidator
): AuthnService {
    override fun login(command: AuthnServiceCommand.Login): String {
        val user = this.usersValidator.validatePassword(event = command.toValidatePasswordEvent())

        user.logined()

        return this.tokenProvider.generateAccessToken(event = command.toGenerateAccessTokenEvent(userId = user.id))
    }

    override fun logout(command: AuthnServiceCommand.Logout) {
        TODO("Not yet implemented")
    }
}