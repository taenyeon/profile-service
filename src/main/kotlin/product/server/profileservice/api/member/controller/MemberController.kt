package product.server.profileservice.api.member.controller

import product.server.profileservice.api.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import product.server.profileservice.common.function.logger
import product.server.profileservice.common.http.constant.ResponseCode
import product.server.profileservice.common.http.domain.Response

@RestController
@RequestMapping("/api/member")
class MemberController(private val memberService: MemberService) {
    val log = logger()

    @GetMapping("/{username}")
    fun findUser(@PathVariable username: String): ResponseEntity<Response> {
        val member = memberService.findMemberDto(username)
        return ResponseCode.SUCCESS.toResponse(member)
    }

}