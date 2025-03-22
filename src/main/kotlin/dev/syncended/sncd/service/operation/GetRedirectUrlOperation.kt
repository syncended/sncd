package dev.syncended.sncd.service.operation

import dev.syncended.sncd.repository.url.UrlRepository
import dev.syncended.sncd.service.encoding.DecodeUrlKeyUseCase
import dev.syncended.sncd.service.validation.ValidateUrlKeyUseCase

class GetRedirectUrlOperation(
    private val validateUrlKeyUseCase: ValidateUrlKeyUseCase,
    private val decodeUrlKeyUseCase: DecodeUrlKeyUseCase,
    private val urlRepository: UrlRepository
) {

    suspend operator fun invoke(key: String?): Result<String> {
        key ?: return Result.failure(NullPointerException("Key is null"))
        return validateUrlKeyUseCase(key)
            .mapCatching { decodeUrlKeyUseCase(key).getOrThrow() }
            .mapCatching { urlRepository.find(it).getOrThrow() }
            .map { it.url }
    }
}