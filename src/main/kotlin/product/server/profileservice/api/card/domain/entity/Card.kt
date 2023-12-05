package product.server.profileservice.api.card.domain.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime

@Entity
@Table(name = "card")
class Card(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,

    var userId: Long?,

    var name: String?,

    @OneToMany(mappedBy = "card", cascade = [CascadeType.ALL])
    var components: MutableList<CardComponent> = mutableListOf<CardComponent>(),

    @CreationTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var createdAt: LocalDateTime? = LocalDateTime.now(),

    @UpdateTimestamp
    @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    var updatedAt: LocalDateTime? = LocalDateTime.now()

) {
    fun addComponent(component: CardComponent) {
        this.components.add(component)
        component.card = this
    }

    fun removeComponent(component: CardComponent) {
        this.components.remove(component)
        component.card = null
    }

}