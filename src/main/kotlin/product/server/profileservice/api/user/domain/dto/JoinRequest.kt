package product.server.profileservice.api.user.domain.dto

import jakarta.validation.constraints.NotNull

data class JoinRequest(
    @NotNull
    var username: String,
    @NotNull
    var password: String,
    @NotNull
    var name: String,
    @NotNull
    var phoneNumber: String,
) {
}