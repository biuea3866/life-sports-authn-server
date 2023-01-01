package biuea.lifesports.authnserver.common.exception

import biuea.lifesports.authnserver.common.exception.BaseException
import org.springframework.http.HttpStatus

class UnauthorizedKeyException : BaseException {
    constructor() : super(HttpStatus.UNAUTHORIZED, "유효하지 않은 키입니다.") {}
}