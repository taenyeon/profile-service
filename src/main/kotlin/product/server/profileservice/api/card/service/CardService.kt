package product.server.profileservice.api.card.service

import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import product.server.profileservice.api.card.domain.dto.CardDto
import product.server.profileservice.api.card.domain.entity.Card
import product.server.profileservice.api.card.domain.mapper.CardMapper
import product.server.profileservice.api.card.repository.CardRepository
import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.function.user
import product.server.profileservice.common.http.constant.ResponseCode

@Service
class CardService(
    private var cardRepository: CardRepository,
) {

    private var cardMapper: CardMapper = Mappers.getMapper(CardMapper::class.java)

    fun add(cardDto: CardDto) {
        cardDto.userId = user().id!!
        val card = cardMapper.toEntity(cardDto)
        cardRepository.save(card)
    }

    fun select(id: Long): Card {
        return cardRepository.findById(id)
            .orElseThrow { ResponseException(ResponseCode.INVALID_REQUEST_PARAM) }
    }

    fun selectDto(id: Long): CardDto {
        return cardMapper.toDto(select(id))
    }

    fun selectList(userId: Long): List<Card> {
        return cardRepository.findByUserId(userId)
    }

    fun selectDtoList(userId: Long): List<CardDto> {
        return selectList(userId).map { card -> cardMapper.toDto(card) }
    }


    fun delete(id: Long) {
        cardRepository.deleteById(id)
    }

}