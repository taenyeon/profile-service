package product.server.profileservice.api.member.repository

import product.server.profileservice.api.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface MemberRepository : JpaRepository<Member, Long> {

    fun findByUsername(username: String?): Optional<Member>

}