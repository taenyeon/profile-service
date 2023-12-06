package product.server.profileservice.api.card.domain.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer
import jakarta.validation.constraints.NotNull
import org.springframework.format.annotation.DateTimeFormat
import product.server.profileservice.api.card.domain.entity.CardComponent
import java.time.LocalDateTime

class CardDto(

    var id: Long?,

    var userId: Long,

    @NotNull
    var name: String,

    var components: MutableList<CardComponentDto> = mutableListOf(),

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss")
//    @JsonDeserialize(using = LocalDateTimeDeserializer::class)
    var updatedAt: LocalDateTime? = LocalDateTime.now()

) {
}