package dev.syncended.sncd.service.validation

import dev.syncended.sncd.model.exception.InvalidUrlException

class ValidateUrlUseCase {

    operator fun invoke(url: String): Result<Unit> = if (URL_REGEX.matches(url)) {
        Result.success(Unit)
    } else {
        Result.failure(InvalidUrlException(url))
    }

    private companion object {
        val URL_REGEX =
            Regex("https?://(www\\.)?[-a-zA-Z0-9@:%._+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b([-a-zA-Z0-9()@:%_+.~#?&/=]*)")
    }
}