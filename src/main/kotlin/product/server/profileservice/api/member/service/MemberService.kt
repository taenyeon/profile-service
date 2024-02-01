package product.server.profileservice.api.member.service

import product.server.profileservice.api.member.domain.mapper.MemberMapper
import product.server.profileservice.api.member.domain.dto.response.MemberResponse
import product.server.profileservice.api.member.domain.entity.Member
import product.server.profileservice.api.member.repository.MemberRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.function.encrypt
import product.server.profileservice.common.function.logger
import product.server.profileservice.common.http.constant.ResponseCode

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    val log = logger()

    // mapStruct
    private val memberDtoMapper: MemberMapper = Mappers.getMapper(MemberMapper::class.java)


    // Entity


    fun addMember(member: Member) {
        checkExistMember(member.username!!)
        memberRepository.save(member)
    }

    fun deleteMember(id: Long) {
        try {
            memberRepository.deleteById(id)
        } catch (e: Exception) {
            throw ResponseException(ResponseCode.NOT_FOUND_ERROR, e)
        }
    }


    fun findMember(username: String): Member {
        log.info("username : $username")
        return memberRepository.findByUsername(username.encrypt())
            .orElseThrow { ResponseException(ResponseCode.NOT_FOUND_ERROR) }
    }

    fun checkExistMember(username: String) {
        memberRepository.findByUsername(username.encrypt())
            .orElseThrow { throw ResponseException(ResponseCode.EXIST_MEMBER) }
    }

    fun findMember(id: Long): Member {
        return memberRepository.findById(id)
            .orElseThrow { ResponseException(ResponseCode.NOT_FOUND_ERROR) }
    }

    // Dto
    fun findMemberDto(username: String): MemberResponse {
        return memberDtoMapper.toDto(findMember(username))
    }

    fun findMemberDto(id: Long): MemberResponse {
        return memberDtoMapper.toDto(findMember(id))
    }
}