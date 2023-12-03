package product.server.profileservice.api.member.service

import product.server.profileservice.api.member.domain.entity.Member
import product.server.profileservice.api.member.repository.MemberRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.http.constant.ResponseCode

@Service
class MemberCacheService(
    private val memberRepository: MemberRepository
) {

    @Cacheable(value = ["member"], key = "#id")
    fun findMember(id: Long): Member {
        return memberRepository.findById(id)
            .orElseThrow { ResponseException(ResponseCode.NOT_FOUND_ERROR) }
    }
}