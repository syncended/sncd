package dev.syncended.sncd.service.validation

import dev.syncended.sncd.model.Encoding
import dev.syncended.sncd.model.exception.BrokenEncodingException
import dev.syncended.sncd.model.exception.InvalidUrlKeyException

class ValidateUrlKeyUseCase {

    operator fun invoke(key: String): Result<Unit> {
        if (key.length < 4) return Result.failure(InvalidUrlKeyException(key))
        val charSet = Encoding.CHAR_TO_INDEX.keys
        val isValidChars = key.all { it in charSet }
        return if (isValidChars) {
            Result.success(Unit)
        } else {
            Result.failure(BrokenEncodingException(key))
        }
    }
}