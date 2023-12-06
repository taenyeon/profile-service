package product.server.profileservice.api.card.repository

import product.server.profileservice.api.member.domain.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import product.server.profileservice.api.card.domain.entity.Card
import java.util.Optional

@Repository
interface CardRepository : JpaRepository<Card, Long> {
    fun findByUserId(id:Long):List<Card>
}