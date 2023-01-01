package biuea.lifesports.authnserver.common.exception

import org.springframework.http.HttpStatus

open class BaseException : RuntimeException {
    val statusCode: HttpStatus
    var code: String? = null

    override var message: String? = null

    constructor(
        statusCode: HttpStatus,
        message: String?
    ) : super() {
        this.statusCode = statusCode
        this.message = message
    }

    constructor(
        statusCode: HttpStatus,
        message: String?,
        code: String?
    ) : super(message) {
        this.statusCode = statusCode
        this.message = message
        this.code = code
    }
}