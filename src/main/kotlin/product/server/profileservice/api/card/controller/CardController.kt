package product.server.profileservice.api.card.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import product.server.profileservice.api.card.domain.dto.CardDto
import product.server.profileservice.api.card.service.CardService
import product.server.profileservice.common.function.user
import product.server.profileservice.common.http.constant.ResponseCode
import product.server.profileservice.common.http.domain.Response

@RestController
@RequestMapping("/api/card")
class CardController(private var cardService: CardService) {

    @PostMapping("")
    fun add(@RequestBody cardDto: CardDto): ResponseEntity<Response> {
        return ResponseCode.SUCCESS.toResponse(cardService.add(cardDto))
    }

    @GetMapping("/{id}")
    fun select(@PathVariable id: Long): ResponseEntity<Response> {
        return ResponseCode.SUCCESS.toResponse(cardService.select(id))
    }

    @GetMapping("/user/{userId}")
    fun selectListByUserId(@PathVariable userId: Long): ResponseEntity<Response> {
        return ResponseCode.SUCCESS.toResponse(cardService.selectList(userId))
    }
}