package product.server.profileservice.common.annotation.encrypt

import org.mapstruct.Qualifier

@Qualifier
@Target(AnnotationTarget.TYPE, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
annotation class Decrypt()
