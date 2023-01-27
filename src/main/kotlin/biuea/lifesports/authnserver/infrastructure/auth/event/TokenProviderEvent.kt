package biuea.lifesports.authnserver.infrastructure.auth.event

class TokenProviderEvent {
    class GenerateAccessToken(
        val email: String,
        val userId: Long
    )
    class GenerateRefreshToken()
    class ValidateAccessToken(val token: String)
    class ValidateRefreshToken()
}