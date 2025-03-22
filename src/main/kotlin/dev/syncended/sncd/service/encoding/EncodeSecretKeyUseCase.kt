package dev.syncended.sncd.service.encoding

import dev.syncended.sncd.model.Encoding
import dev.syncended.sncd.model.Encoding.SECRET_KEY_LENGTH

class EncodeSecretKeyUseCase(
    private val encodeNumberUseCase: EncodeNumberUseCase
) {

    operator fun invoke(keyValue: Int): String = encodeNumberUseCase(keyValue)
        .padStart(SECRET_KEY_LENGTH, Encoding.ZERO_CHAR)
}