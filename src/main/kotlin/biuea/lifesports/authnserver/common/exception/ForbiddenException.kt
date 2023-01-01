package biuea.lifesports.authnserver.common.exception

import biuea.lifesports.authnserver.common.exception.BaseException
import org.springframework.http.HttpStatus

class ForbiddenException : BaseException {
    constructor(message: String) : super(
        statusCode = HttpStatus.FORBIDDEN,
        message = message
    )
    constructor(error: CommonError) : super(
        statusCode = HttpStatus.FORBIDDEN,
        message = error.message,
        code = error.code
    )
}