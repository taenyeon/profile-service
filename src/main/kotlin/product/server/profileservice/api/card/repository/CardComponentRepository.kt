package product.server.profileservice.api.card.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import product.server.profileservice.api.card.domain.entity.CardComponent

@Repository
interface CardComponentRepository : JpaRepository<CardComponent, Long> {

}