package product.server.profileservice.api.user.domain.mapper

import product.server.profileservice.api.user.domain.dto.JoinRequest
import product.server.profileservice.api.member.domain.entity.Member
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import product.server.profileservice.common.annotation.encrypt.Encrypt
import product.server.profileservice.common.annotation.encrypt.PasswordEncrypt
import product.server.profileservice.common.interfaces.EntityMapper
import product.server.profileservice.common.util.EncryptUtil

@Mapper(uses = [EncryptUtil::class])
interface JoinRequestMapper : EntityMapper<Member, JoinRequest> {

    @Mapping(source = "password", target = "password", qualifiedBy = [PasswordEncrypt::class])
    @Mapping(source = "username", target = "username", qualifiedBy = [Encrypt::class])
    @Mapping(source = "name", target = "name", qualifiedBy = [Encrypt::class])
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedBy = [Encrypt::class])
    override fun toEntity(dto: JoinRequest): Member

    override fun toDto(entity: Member): JoinRequest
}