package dev.syncended.sncd.service.encoding

import dev.syncended.sncd.model.Encoding.SECRET_KEY_LENGTH
import dev.syncended.sncd.model.url.UrlKey
import dev.syncended.sncd.model.url.toUrlId

class DecodeUrlKeyUseCase(
    private val decodeNumberUseCase: DecodeNumberUseCase
) {

    operator fun invoke(key: String): Result<UrlKey> = runCatching {
        val encodedSecretKey = key.take(SECRET_KEY_LENGTH)
        val encodedId = key.substring(SECRET_KEY_LENGTH)
        UrlKey(
            id = decodeNumberUseCase(encodedId).getOrThrow().toUrlId(),
            secretKey = decodeNumberUseCase(encodedSecretKey).getOrThrow()
        )
    }
}