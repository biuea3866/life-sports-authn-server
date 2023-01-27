package biuea.lifesports.authnserver.infrastructure.user.validator

import biuea.lifesports.authnserver.common.exception.ForbiddenException
import biuea.lifesports.authnserver.common.exception.NotFoundException
import biuea.lifesports.authnserver.domain.auth.error.AuthnErrors
import biuea.lifesports.authnserver.domain.user.entity.User
import biuea.lifesports.authnserver.infrastructure.user.UsersRepository
import biuea.lifesports.authnserver.infrastructure.user.event.UsersReaderEvent
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.stereotype.Component

@Component
class UsersValidatorImpl(val usersRepository: UsersRepository): UsersValidator {
    override fun validatePassword(event: UsersReaderEvent.ValidatePassword): User {
        val user = this.usersRepository.findByEmail(email = event.email)
            .orElseThrow { throw NotFoundException(AuthnErrors.of(error = AuthnErrors.NOT_FOUND_USER)) }

        if (BCrypt.checkpw(event.password, user.password)) {
            throw ForbiddenException(AuthnErrors.of(error = AuthnErrors.NOT_MATCH_PASSWORD))
        }

        return user
    }
}