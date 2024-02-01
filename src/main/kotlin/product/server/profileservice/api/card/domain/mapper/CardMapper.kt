package product.server.profileservice.api.card.domain.mapper

import org.mapstruct.Mapper
import product.server.profileservice.api.card.domain.dto.CardDto
import product.server.profileservice.api.card.domain.entity.Card
import product.server.profileservice.api.card.domain.entity.CardComponent
import product.server.profileservice.common.interfaces.EntityMapper

@Mapper(
    uses = [
        CardComponentMapper::class,
    ]
)
interface CardMapper : EntityMapper<Card, CardDto> {

    override fun toEntity(dto: CardDto): Card

    override fun toDto(entity: Card): CardDto

    fun toDto(entity: Card, components: List<CardComponent>): CardDto
}