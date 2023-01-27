package biuea.lifesports.authnserver.presentation.auth.request

class AuthnControllerRequest {
    class LoginV1(
        val email: String,
        val password: String
    )
}