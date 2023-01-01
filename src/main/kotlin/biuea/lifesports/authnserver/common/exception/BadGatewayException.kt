package biuea.lifesports.authnserver.common.exception

import org.springframework.http.HttpStatus

class BadGatewayException : BaseException {
    constructor(message: String) : super(
        statusCode = HttpStatus.BAD_GATEWAY,
        message = message
    )
    constructor(error: CommonError) : super(
        statusCode = HttpStatus.BAD_GATEWAY,
        message = error.message,
        code = error.code
    )
}