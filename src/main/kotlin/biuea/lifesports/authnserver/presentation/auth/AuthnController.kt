package biuea.lifesports.authnserver.presentation.auth

import biuea.lifesports.authnserver.application.auth.AuthnFacade
import biuea.lifesports.authnserver.application.auth.input.AuthnFacadeInput
import biuea.lifesports.authnserver.presentation.auth.request.AuthnControllerRequest
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import javax.validation.Valid

@Controller
class AuthnController(val authnFacade: AuthnFacade) {
    @PostMapping(
        value = ["authn/v1.0/login"],
        produces = ["application/json; charset=utf-8"]
    )
    @ResponseBody
    fun loginV1(@Valid @RequestBody body: AuthnControllerRequest.LoginV1): String {
        return this.authnFacade.login(input = AuthnFacadeInput.LoginV1(
            email = body.email,
            password = body.password
        ))
    }

    fun refreshToken() {}

    fun generateToken() {}

    fun logout() {}
}