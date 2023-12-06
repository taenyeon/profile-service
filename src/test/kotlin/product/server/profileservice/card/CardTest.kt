package product.server.profileservice.card

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import product.server.profileservice.api.card.domain.constant.CardComponentType
import product.server.profileservice.api.card.domain.entity.Card
import product.server.profileservice.api.card.domain.entity.CardComponent
import product.server.profileservice.api.card.domain.mapper.CardMapper
import product.server.profileservice.api.card.repository.CardComponentRepository
import product.server.profileservice.api.card.repository.CardRepository
import product.server.profileservice.common.exception.ResponseException
import product.server.profileservice.common.function.logger
import product.server.profileservice.common.http.constant.ResponseCode

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardTest(
    @Autowired
    private val cardComponentRepository: CardComponentRepository,
    @Autowired
    private val cardRepository: CardRepository
) {

    private var card: Card? = null
    private var cardComponent: CardComponent? = null
    private var cardMapper = Mappers.getMapper(CardMapper::class.java)
    private var objectMapper: ObjectMapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        objectMapper.registerModules(JavaTimeModule())
        card = Card(
            id = null,
            userId = 1,
            name = "테스트 명함"
        )

        cardComponent = CardComponent(
            id = null,
            card = card,
            type = CardComponentType.TEXT,
            name = "테스트 컴퍼넌트",
            payload = "페이로드",
            x = 0,
            y = 0
        )
        card?.addComponent(component = cardComponent!!)
    }

    @Test
    fun insert() {
        cardRepository.save(card!!)
        cardComponentRepository.save(cardComponent!!)
    }

    @Test
    fun select() {
        val save = cardRepository.save(card!!)
        val result =
            cardRepository.findById(save.id!!).orElseThrow { ResponseException(ResponseCode.INVALID_REQUEST_PARAM) }
        val writeValueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(cardMapper.toDto(result))
        logger().info("result : $writeValueAsString")
    }

    fun delete() {

    }

    fun update() {

    }
}