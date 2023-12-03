package product.server.profileservice.api.user.domain.dto

class JwtToken(
    val accessToken: String,
    val refreshToken: String
) {
}