package product.server.profileservice.api.member.domain.mapper

import product.server.profileservice.api.member.domain.dto.response.MemberResponse
import product.server.profileservice.api.member.domain.entity.Member
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import product.server.profileservice.common.annotation.encrypt.Decrypt
import product.server.profileservice.common.annotation.encrypt.Encrypt
import product.server.profileservice.common.interfaces.EntityMapper
import product.server.profileservice.common.util.EncryptUtil

@Mapper(uses = [EncryptUtil::class])
interface MemberMapper : EntityMapper<Member, MemberResponse> {

    @Mapping(source = "username", target = "username", qualifiedBy = [Encrypt::class])
    @Mapping(source = "name", target = "name", qualifiedBy = [Encrypt::class])
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedBy = [Encrypt::class])
    override fun toEntity(dto: MemberResponse): Member

    @Mapping(source = "username", target = "username", qualifiedBy = [Decrypt::class])
    @Mapping(source = "name", target = "name", qualifiedBy = [Decrypt::class])
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedBy = [Decrypt::class])
    override fun toDto(entity: Member): MemberResponse

}