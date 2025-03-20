package dev.syncended.sncd.service.encoding

import dev.syncended.sncd.model.Encoding
import dev.syncended.sncd.model.exception.BrokenEncodingException

class DecodeNumberUseCase {

    operator fun invoke(encoded: String): Result<Int> {
        val decoded = encoded.foldIndexed(0) { index, acc, char ->
            val index = Encoding.CHAR_TO_INDEX[char] ?: return Result.failure(BrokenEncodingException(encoded))
            acc * Encoding.BASE + index
        }
        return Result.success(decoded)
    }
}