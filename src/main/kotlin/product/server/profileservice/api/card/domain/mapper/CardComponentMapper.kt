package product.server.profileservice.api.card.domain.mapper

import org.mapstruct.Mapper
import product.server.profileservice.api.card.domain.dto.CardComponentDto
import product.server.profileservice.api.card.domain.entity.CardComponent
import product.server.profileservice.common.interfaces.EntityMapper

@Mapper
interface CardComponentMapper :
    EntityMapper<CardComponent, CardComponentDto> {
    override fun toEntity(dto: CardComponentDto) :CardComponent

    override fun toDto(entity: CardComponent): CardComponentDto
}