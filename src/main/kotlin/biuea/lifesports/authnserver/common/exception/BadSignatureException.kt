package biuea.lifesports.authnserver.common.exception

import biuea.lifesports.authnserver.common.exception.BaseException
import org.springframework.http.HttpStatus

class BadSignatureException(message: String?) : BaseException(HttpStatus.BAD_REQUEST, message)