package biuea.lifesports.authnserver.common.exception

import biuea.lifesports.authnserver.common.exception.BaseException
import org.springframework.http.HttpStatus

class TokenExpiredException : BaseException {
    constructor() : super(HttpStatus.BAD_REQUEST, "인증 링크 정보가 만료되었습니다.") {}
}