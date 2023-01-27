package biuea.lifesports.authnserver.application.auth.input

import biuea.lifesports.authnserver.domain.auth.command.AuthnServiceCommand

class AuthnFacadeInput {
    class LoginV1(
        val email: String,
        val password: String
    ) {
        fun of(): AuthnServiceCommand.Login {
            return AuthnServiceCommand.Login(
                email = this.email,
                password = this.password
            )
        }
    }
}