package biuea.lifesports.authnserver.presentation.auth.request

class AuthnControllerRequest {
    data class LoginV1(
        val email: String,
        val password: String
    )
}