package dev.syncended.sncd.service.operation

import dev.syncended.sncd.service.validation.ValidateUrlUseCase

class CreateShortUrlUseCase(
    private val validateUrl: ValidateUrlUseCase
) {

    suspend fun invoke(url: String): Result<String> {
        return validateUrl.invoke(url)
            .map { "" }
    }
}