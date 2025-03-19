package dev.syncended.sncd.service.encoding

import dev.syncended.sncd.model.Encoding
import dev.syncended.sncd.service.url.GenerateSecretKeyUseCase.Companion.SECRET_KEY_LENGTH

class EncodeSecretKeyUseCase(
    private val encodeNumber: EncodeNumberUseCase
) {

    fun invoke(keyValue: Int): String {
        encodeNumber.invoke(keyValue).padStart(SECRET_KEY_LENGTH, Encoding.ZERO_CHAR)
    }

    private companion object {
        const val SECRET_KEY_LENGTH = 3
    }
}