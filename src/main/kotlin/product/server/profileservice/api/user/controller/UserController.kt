package product.server.profileservice.api.user.controller

import product.server.profileservice.api.user.domain.dto.JwtToken
import product.server.profileservice.api.user.domain.dto.LoginRequest
import product.server.profileservice.api.user.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import product.server.profileservice.api.user.domain.dto.JoinRequest
import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.function.logger
import product.server.profileservice.common.function.user
import product.server.profileservice.common.http.constant.ResponseCode
import product.server.profileservice.common.http.domain.Response

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {
    val log = logger()

    @PostMapping("")
    fun join(@RequestBody joinRequest: JoinRequest): ResponseEntity<Response> {
        return ResponseCode.SUCCESS.toResponse(userService.join(joinRequest))
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Response> {
        return ResponseCode.SUCCESS.toResponse(userService.login(loginRequest))
    }

    @DeleteMapping("/logout")
    fun logout(): ResponseEntity<Response> {
        val user = user()
        log.info("logoutUser - id : ${user.id}")
        userService.logout(user.id!!)
        return ResponseCode.SUCCESS.toResponse()
    }

    @GetMapping("/accessToken")
    fun reIssueAccessToken(@RequestHeader(name = "refresh_token") refreshToken: String?): ResponseEntity<Response> {

        if (refreshToken.isNullOrBlank()) {
            throw ResponseException(ResponseCode.INVALID_REQUEST_PARAM)
        }

        val jwtToken = JwtToken(
            userService.reIssueAccessToken(refreshToken),
            refreshToken
        )
        return ResponseCode.SUCCESS.toResponse(jwtToken)
    }


}