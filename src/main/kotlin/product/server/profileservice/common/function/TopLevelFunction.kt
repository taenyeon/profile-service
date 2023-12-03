package product.server.profileservice.common.function

import product.server.profileservice.api.member.domain.entity.Member
import product.server.profileservice.api.user.domain.dto.User
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import product.server.profileservice.common.util.EncryptUtil

// LOGGING
inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)!!

// USER
fun user() = Member(SecurityContextHolder.getContext().authentication.details as User)

fun String.encrypt() = EncryptUtil().encrypt(this)

fun String.decrypt() = EncryptUtil().decrypt(this)