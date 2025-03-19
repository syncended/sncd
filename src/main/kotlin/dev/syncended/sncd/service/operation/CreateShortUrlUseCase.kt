package dev.syncended.sncd.service.operation

import dev.syncended.sncd.repository.url.UrlRepository
import dev.syncended.sncd.service.url.ExtractShortUrlUseCase
import dev.syncended.sncd.service.url.GenerateUrlUseCase
import dev.syncended.sncd.service.validation.ValidateUrlUseCase

class CreateShortUrlUseCase(
    private val extractShortUrl: ExtractShortUrlUseCase,
    private val generateUrl: GenerateUrlUseCase,
    private val validateUrl: ValidateUrlUseCase,
    private val urlRepository: UrlRepository,
) {

    suspend fun invoke(url: String): Result<String> {
        return validateUrl.invoke(url)
            .map { generateUrl.invoke(url) }
            .mapCatching { urlRepository.insert(it).getOrThrow() }
            .map { extractShortUrl.invoke(it) }
    }
}