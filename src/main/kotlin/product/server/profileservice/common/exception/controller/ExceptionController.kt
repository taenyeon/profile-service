package product.server.profileservice.common.exception.controller

import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.function.logger
import product.server.profileservice.common.http.constant.ResponseCode
import product.server.profileservice.common.http.domain.Response
import org.springframework.http.ResponseEntity
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    val log = logger()

    @ExceptionHandler(value = [Exception::class])
    fun unknownExceptionHandler(e: Exception): ResponseEntity<Response> {
        e.printStackTrace()
        log.error("[unknownException Error] - errorMessage : ${e.message}")
        return ResponseCode.UNKNOWN_ERROR.toResponse()
    }

    @ExceptionHandler(value = [ResponseException::class])
    fun responseExceptionHandler(e: ResponseException): ResponseEntity<Response> {
        e.printStackTrace()
        val responseCode = e.responseCode
        log.error("[responseExceptionHandler] - errorCase : $e")
        return responseCode.toResponse()
    }

    @ExceptionHandler(value = [AuthenticationException::class])
    fun authExceptionHandler(e: AuthenticationException): ResponseEntity<Response> {
        return ResponseCode.AUTH_ERROR.toResponse()
    }

    @ExceptionHandler(value = [AccessDeniedException::class])
    fun accessDeniedExceptionHandler(e: AccessDeniedException): ResponseEntity<Response> {
        return ResponseCode.ACCESS_DENIED_ERROR.toResponse()
    }
}