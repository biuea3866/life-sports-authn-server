package biuea.lifesports.authnserver.infrastructure.auth.provider

import biuea.lifesports.authnserver.common.exception.NotFoundException
import biuea.lifesports.authnserver.domain.auth.dto.TokenClaim
import biuea.lifesports.authnserver.domain.auth.error.AuthnErrors
import biuea.lifesports.authnserver.infrastructure.auth.event.TokenProviderEvent
import biuea.lifesports.authnserver.infrastructure.user.UsersRepository
import io.jsonwebtoken.Header
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class TokenProviderImpl(
    @Value("\${jwt.key}")
    val jwtKey: String,
    @Value("\${jwt.access-time}")
    val accessTime: Long,
    @Value("\${jwt.refresh-time}")
    val refreshTime: Long,
    val usersRepository: UsersRepository
): TokenProvider {
    override fun generateAccessToken(event: TokenProviderEvent.GenerateAccessToken): String {
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
            .setExpiration(Date.from(ZonedDateTime.now().plusMinutes((accessTime / 60)).toInstant()))
            .claim("userId", event.userId)
            .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(jwtKey.encodeToByteArray()))
            .compact()
    }

    override fun generateRefreshToken(event: TokenProviderEvent.GenerateRefreshToken): String {
        TODO("Not yet implemented")
    }

    override fun validateAccessToken(event: TokenProviderEvent.ValidateAccessToken): TokenClaim {
        val claim = Jwts.parserBuilder()
            .setSigningKey(Base64.getEncoder().encodeToString(jwtKey.encodeToByteArray()))
            .build()
            .parseClaimsJws(event.token)
            .body

        println("userId: ${claim["userId"]}")
        println("email: ${claim["email"]}")
        println("exp: ${claim["exp"]}")

        return TokenClaim(
            userId = claim["userId"].toString().toLong(),
            email = claim["email"] as String?,
            exp = claim["exp"].toString().toLong()
        )
    }

    override fun validateRefreshToken(event: TokenProviderEvent.ValidateRefreshToken): TokenClaim {
        TODO("Not yet implemented")
    }
}