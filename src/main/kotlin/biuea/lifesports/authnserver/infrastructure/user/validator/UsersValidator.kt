package biuea.lifesports.authnserver.infrastructure.user.validator

import biuea.lifesports.authnserver.domain.user.entity.User
import biuea.lifesports.authnserver.infrastructure.user.event.UsersReaderEvent

interface UsersValidator {
    fun validatePassword(event: UsersReaderEvent.ValidatePassword): User
}