package biuea.lifesports.authnserver.service.error

import biuea.lifesports.authnserver.common.exception.CommonError

enum class AuthnErrors(
    val code: String,
    val message: String
) {
    HAS_NULL_AMONG_TOKEN_FIELD(
        code = "AUTHN-000001",
        message = "토큰 필드 중 빈 값이 존재합니다."
    ),
    HAS_NOT_AUTHORIZATION(
        code = "AUTHN-000002",
        message = "요청 헤더에 Authorization 키 값이 존재하지 않습니다."
    ),
    HAS_NOT_TOKEN(
        code = "AUTHN-000003",
        message = "토큰 값이 존재하지 않습니다."
    ),
    HAS_NOT_API_TYPE(
        code = "AUTHN-000004",
        message = "api 타입이 존재하지 않습니다."
    ),
    NOT_FOUND_USER(
        code = "AUTHN-000005",
        message = "존재하지 않는 유저입니다."
    ),
    NOT_MATCH_ID_WITH_EMAIL(
        code = "AUTHN-000006",
        message = "유저 정보와 이메일이 일치하지 않습니다."
    ),
    NOT_FOUND_OPEN_API_KEY(
        code = "AUTHN-000007",
        message = "Open API 값이 존재하지 않습니다."
    )
    ;

    companion object {
        fun of(error: AuthnErrors): CommonError {
            return CommonError(
                code = error.code,
                message = error.message
            )
        }
    }
}