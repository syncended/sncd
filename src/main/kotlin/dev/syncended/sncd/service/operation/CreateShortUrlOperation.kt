package dev.syncended.sncd.service.operation

import dev.syncended.sncd.repository.url.UrlRepository
import dev.syncended.sncd.service.url.ExtractShortUrlUseCase
import dev.syncended.sncd.service.url.GenerateUrlUseCase
import dev.syncended.sncd.service.validation.ValidateUrlUseCase

class CreateShortUrlOperation(
    private val extractShortKeyUseCase: ExtractShortUrlUseCase,
    private val generateUrlUseCase: GenerateUrlUseCase,
    private val validateUrlUseCase: ValidateUrlUseCase,
    private val urlRepository: UrlRepository,
) {

    suspend operator fun invoke(url: String): Result<String> {
        return validateUrlUseCase(url)
            .map { generateUrlUseCase(url) }
            .mapCatching { urlRepository.insert(it).getOrThrow() }
            .map { extractShortKeyUseCase(it) }
    }
}