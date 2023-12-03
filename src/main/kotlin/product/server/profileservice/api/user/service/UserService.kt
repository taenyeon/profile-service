package product.server.profileservice.api.user.service

import product.server.profileservice.api.member.service.MemberService
import product.server.profileservice.api.member.domain.entity.Member
import product.server.profileservice.api.user.domain.dto.JwtToken
import product.server.profileservice.api.user.domain.dto.LoginRequest
import product.server.profileservice.api.user.domain.mapper.UserMapper
import org.mapstruct.factory.Mappers
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import product.server.profileservice.api.user.domain.dto.JoinRequest
import product.server.profileservice.api.user.domain.mapper.JoinRequestMapper
import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.function.user
import product.server.profileservice.common.http.constant.ResponseCode
import product.server.profileservice.common.security.constant.TokenStatus
import product.server.profileservice.common.security.provider.JwtTokenProvider
import java.util.*

@Service
class UserService(
    private val memberService: MemberService,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider
) {
    private val userMapper: UserMapper = Mappers.getMapper(UserMapper::class.java)

    private val joinRequestMapper: JoinRequestMapper = Mappers.getMapper(JoinRequestMapper::class.java)

    fun getUser(): Member {
        return user()
    }

    fun join(joinRequest: JoinRequest) {
        val member = joinRequestMapper.toEntity(joinRequest)
        memberService.addMember(member)
    }

    fun login(loginRequest: LoginRequest): JwtToken {
        val member: Member = memberService.findMember(loginRequest.username)
        matchingPassword(loginRequest.password, member.password!!)
        return jwtTokenProvider.generateToken(member.id!!)
    }

    fun logout(id: Long) {
        jwtTokenProvider.dropRefreshToken(id)
    }

    fun reIssueAccessToken(refreshToken: String): String {
        when (jwtTokenProvider.validateToken(refreshToken)) {
            TokenStatus.ALLOW -> {
                val id = jwtTokenProvider.parseIdFromJWT(refreshToken)
                return jwtTokenProvider.generateAccessToken(id, now = Date())
            }

            else -> throw ResponseException(ResponseCode.INVALID_TOKEN)
        }
    }

    fun matchingPassword(rawPassword: String, encodedPassword: String) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword))
            throw ResponseException(ResponseCode.WRONG_PASSWORD_ERROR)
    }
}