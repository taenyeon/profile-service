package product.server.profileservice.api.user.domain.mapper

import product.server.profileservice.api.member.domain.entity.Member
import product.server.profileservice.api.user.domain.dto.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import product.server.profileservice.common.annotation.encrypt.Decrypt
import product.server.profileservice.common.annotation.encrypt.Encrypt
import product.server.profileservice.common.util.EncryptUtil

@Mapper(uses = [EncryptUtil::class])
interface UserMapper {
    @Mapping(source = "username", target = "username", qualifiedBy = [Encrypt::class])
    @Mapping(source = "name", target = "name", qualifiedBy = [Encrypt::class])
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedBy = [Encrypt::class])
    fun toMember(dto: User): Member

    @Mapping(source = "username", target = "username", qualifiedBy = [Decrypt::class])
    @Mapping(source = "name", target = "name", qualifiedBy = [Decrypt::class])
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedBy = [Decrypt::class])
    fun toUser(entity: Member): User
}