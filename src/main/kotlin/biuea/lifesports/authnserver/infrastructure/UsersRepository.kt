package biuea.lifesports.authnserver.infrastructure

import biuea.lifesports.authnserver.service.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsersRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}