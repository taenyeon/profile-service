package product.server.profileservice.api.card.domain.dto

import org.springframework.format.annotation.DateTimeFormat
import product.server.profileservice.api.card.domain.constant.CardComponentType
import java.time.LocalDateTime

class CardComponentDto(
    var id: Long,

    var type: CardComponentType,

    var x: Int,

    var y: Int,

    var name: String,

    var payload: String,

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
) {
}