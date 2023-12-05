package product.server.profileservice.api.card.domain.dto

import product.server.profileservice.api.card.domain.constant.CardComponentType
import java.time.LocalDateTime

class CardComponentDto(
    var id: Long,

    var card: CardDto,

    var type: CardComponentType,

    var x: Int,

    var y: Int,

    var name: String,

    var payload: String,

    var createdAt: LocalDateTime? = LocalDateTime.now(),

    var updatedAt: LocalDateTime? = LocalDateTime.now()
) {
}