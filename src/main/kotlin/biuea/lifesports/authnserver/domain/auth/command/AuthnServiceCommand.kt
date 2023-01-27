package biuea.lifesports.authnserver.domain.auth.command

import biuea.lifesports.authnserver.infrastructure.auth.event.TokenProviderEvent
import biuea.lifesports.authnserver.infrastructure.user.event.UsersReaderEvent

class AuthnServiceCommand {
    class Login(
        val email: String,
        val password: String
    ) {
        fun toValidatePasswordEvent(): UsersReaderEvent.ValidatePassword {
            return UsersReaderEvent.ValidatePassword(
                email = this.email,
                password = this.password
            )
        }

        fun toGenerateAccessTokenEvent(userId: Long): TokenProviderEvent.GenerateAccessToken {
            return TokenProviderEvent.GenerateAccessToken(
                email = this.email,
                userId = userId
            )
        }
    }

    class Logout()
}