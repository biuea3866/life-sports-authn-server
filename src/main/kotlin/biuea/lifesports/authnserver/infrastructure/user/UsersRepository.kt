package biuea.lifesports.authnserver.infrastructure.user

import biuea.lifesports.authnserver.domain.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsersRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}