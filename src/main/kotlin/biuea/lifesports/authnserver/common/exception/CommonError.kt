package biuea.lifesports.authnserver.common.exception

import biuea.lifesports.authnserver.service.error.AuthnErrors

class CommonError(
    val code: String?,
    val message: String?
) {
    companion object {
        fun of(error: AuthnErrors): CommonError {
            return CommonError(
                code = error.code,
                message = error.message
            )
        }
    }
}