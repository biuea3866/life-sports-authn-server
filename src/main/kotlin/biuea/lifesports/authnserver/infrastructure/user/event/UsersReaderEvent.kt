package biuea.lifesports.authnserver.infrastructure.user.event

class UsersReaderEvent {
    class ValidatePassword(
        val email: String,
        val password: String
    )
}