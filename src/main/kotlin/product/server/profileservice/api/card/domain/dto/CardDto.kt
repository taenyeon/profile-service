package product.server.profileservice.api.card.domain.dto

import product.server.profileservice.api.card.domain.entity.CardComponent
import java.time.LocalDateTime

class CardDto(

    var id: Long,

    var userId: Long,

    var name: String,

    var components: MutableList<CardComponentDto> = mutableListOf(),

    var createdAt: LocalDateTime? = LocalDateTime.now(),

    var updatedAt: LocalDateTime? = LocalDateTime.now()

) {
}