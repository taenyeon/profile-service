package product.server.profileservice.card.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import product.server.profileservice.api.card.domain.constant.CardComponentType
import product.server.profileservice.api.card.domain.entity.Card
import product.server.profileservice.api.card.domain.entity.CardComponent
import product.server.profileservice.api.card.domain.mapper.CardComponentMapper
import product.server.profileservice.api.card.repository.CardComponentRepository
import product.server.profileservice.api.card.repository.CardRepository
import product.server.profileservice.common.function.logger

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
class CardServiceTest(
    @Autowired
    private val cardRepository: CardRepository,
    @Autowired
    private val cardComponentRepository: CardComponentRepository
) {
    val log = logger()
    var cardComponentMapper = Mappers.getMapper(CardComponentMapper::class.java)

    var card: Card = Card(
        id = null,
        userId = 1,
        name = "테스트 명함"
    )
    var cardComponent: CardComponent = CardComponent(
        id = null,
        card = null,
        type = CardComponentType.TEXT,
        name = "테스트 명함 컴포넌트",
        payload = "본문",
        x = 0,
        y = 0,
    )
    var objectMapper: ObjectMapper = ObjectMapper()

    @BeforeEach
    fun setUp() {
        objectMapper.registerModules(JavaTimeModule())
    }

    @Test
    fun insert() {
        card.addComponent(cardComponent)
        cardRepository.save(card)
    }

    @Test
    fun select() {
        val component = cardComponentMapper.toDto(
            cardComponentRepository.findById(8).orElseThrow { IllegalStateException("notFound") })
        val componentStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(component)
        log.info("result : $componentStr")
//        val result = cardRepository.findById(2).orElseThrow { IllegalStateException("notFound") }
//        val writeValueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(result)
//        log.info("result : $writeValueAsString")
    }

}