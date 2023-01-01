package biuea.lifesports.authnserver.common.exception

import biuea.lifesports.authnserver.common.exception.BaseException
import biuea.lifesports.authnserver.common.exception.CommonError
import org.springframework.http.HttpStatus

class InternalServerErrorException : BaseException {
    constructor(message: String) : super(
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR,
        message = message
    )
    constructor(error: CommonError) : super(
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR,
        message = error.message,
        code = error.code
    )
}