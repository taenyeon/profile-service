package product.server.profileservice.api.card.domain.dto

import org.springframework.format.annotation.DateTimeFormat
import product.server.profileservice.api.card.domain.entity.CardComponent
import java.time.LocalDateTime

class CardDto(

    var id: Long,

    var userId: Long,

    var name: String,

    var components: MutableList<CardComponentDto> = mutableListOf(),

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = LocalDateTime.now()

) {
}