package biuea.lifesports.authnserver.application.auth

import biuea.lifesports.authnserver.application.auth.input.AuthnFacadeInput
import biuea.lifesports.authnserver.domain.auth.AuthnService
import org.springframework.stereotype.Component

@Component
class AuthnFacade(val authnService: AuthnService) {
    fun login(input: AuthnFacadeInput.LoginV1): String {
        return this.authnService.login(command = input.of())
    }
}