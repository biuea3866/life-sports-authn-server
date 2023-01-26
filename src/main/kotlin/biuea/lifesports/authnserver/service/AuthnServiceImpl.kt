package biuea.lifesports.authnserver.service

import biuea.lifesports.authnserver.common.exception.NotFoundException
import biuea.lifesports.authnserver.common.exception.UnauthorizedException
import biuea.lifesports.authnserver.infrastructure.OpenAPIKeyRepository
import biuea.lifesports.authnserver.infrastructure.UsersRepository
import biuea.lifesports.authnserver.service.dto.TokenClaim
import biuea.lifesports.authnserver.service.entity.User
import biuea.lifesports.authnserver.service.error.AuthnErrors
import biuea.lifesports.authnserver.service.result.AuthnServiceResult
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class AuthnServiceImpl(
    @Value("\${jwt.key}")
    val jwtKey: String,
    val usersRepository: UsersRepository,
    val openAPIKeyRepository: OpenAPIKeyRepository
): AuthnService {
    override fun decodeToken(token: String): AuthnServiceResult.DecodeToken {
        val claim = parseToken(token = token)

        claim.isValidToken()

        return AuthnServiceResult.DecodeToken(
            userId = claim.userId!!,
            email = claim.email!!,
            exp = claim.exp!!
        )
    }

    private fun parseToken(token: String): TokenClaim {
        val claim = Jwts.parserBuilder()
            .setSigningKey(Base64.getEncoder().encodeToString(jwtKey.encodeToByteArray()))
            .build()
            .parseClaimsJws(token)
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

    @Transactional(readOnly = true)
    override fun validateUser(
        userId: Long,
        email: String
    ): User {
        val user = this.usersRepository.findById(userId)
            .orElseThrow { throw NotFoundException(AuthnErrors.of(error = AuthnErrors.NOT_FOUND_USER)) }

        if (user.email != email) throw UnauthorizedException(AuthnErrors.of(error = AuthnErrors.NOT_MATCH_ID_WITH_EMAIL))

        return user
    }

    @Transactional(readOnly = true)
    override fun validateOpenAPIKey(token: String) {
        if (!this.openAPIKeyRepository.existsByToken(token = token)) throw UnauthorizedException(AuthnErrors.of(error = AuthnErrors.NOT_FOUND_OPEN_API_KEY))
    }
}