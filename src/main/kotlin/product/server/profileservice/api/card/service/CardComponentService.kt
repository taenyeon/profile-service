package product.server.profileservice.api.card.service

import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import product.server.profileservice.api.card.domain.mapper.CardComponentMapper
import product.server.profileservice.api.card.repository.CardComponentRepository

@Service
class CardComponentService(
    private var cardComponentRepository: CardComponentRepository,
) {

    private var cardComponentMapper = Mappers.getMapper(CardComponentMapper::class.java)


}