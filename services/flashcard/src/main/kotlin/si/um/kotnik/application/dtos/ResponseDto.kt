package si.um.kotnik.application.dtos

import jakarta.ws.rs.core.Response

data class ResponseDto<T>(
    var message: String = "",

    var data: T? = null,

    var statusCode: Int = Response.Status.OK.statusCode
) {
    val isSuccessStatusCode: Boolean
        get() = statusCode == Response.Status.OK.statusCode
}