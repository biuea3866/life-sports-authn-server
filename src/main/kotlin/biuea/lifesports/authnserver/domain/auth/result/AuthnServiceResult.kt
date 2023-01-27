package biuea.lifesports.authnserver.domain.auth.result

class AuthnServiceResult {
    class DecodeToken(
        val userId: Long,
        val email: String,
        val exp: Long
    )
}