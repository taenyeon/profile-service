package product.server.profileservice.api.card.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.format.annotation.DateTimeFormat
import product.server.profileservice.api.card.domain.constant.CardComponentType
import java.time.LocalDateTime

@Entity
@Table(name = "card_component")
class CardComponent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    @ManyToOne
    @JoinColumn(name = "card_id")
    var card: Card,

    var type: CardComponentType,

    var x: Int,

    var y: Int,

    var name: String,

    var payload: String,

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = LocalDateTime.now()
) {


}