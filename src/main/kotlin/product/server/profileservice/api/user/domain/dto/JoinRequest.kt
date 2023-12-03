package product.server.profileservice.api.user.domain.dto

data class JoinRequest(
    var username: String?,
    var password: String?,
    var name: String?,
    var phoneNumber: String?,
) {

    constructor() : this(null, null, null, null)
}