package biuea.lifesports.authnserver.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class CommonResponse<T>(
    val success: Boolean,
    val data: T? = null,
    val message: String? = null,
    val code: String? = null
) {
    companion object {
        fun<T> of(
            body: CommonResponse<T?>,
            status: HttpStatus
        ): ResponseEntity<CommonResponse<T?>> {
            return ResponseEntity(
                body,
                status
            )
        }

        fun fail(
            body: CommonResponse<String>,
            status: HttpStatus
        ): ResponseEntity<CommonResponse<*>> {
            return ResponseEntity(
                body,
                status
            )
        }
    }
}