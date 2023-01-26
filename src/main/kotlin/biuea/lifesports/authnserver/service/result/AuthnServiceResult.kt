package biuea.lifesports.authnserver.service.result

class AuthnServiceResult {
    class DecodeToken(
        val userId: Long,
        val email: String,
        val exp: Long
    )
}