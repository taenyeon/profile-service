package product.server.profileservice.common.interfaces

interface EntityMapper<E, D> {

    fun toEntity(dto: D): E
    fun toDto(entity: E): D
}