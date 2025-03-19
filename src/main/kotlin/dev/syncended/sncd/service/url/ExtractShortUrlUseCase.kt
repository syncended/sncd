package dev.syncended.sncd.service.url

import dev.syncended.sncd.model.url.UrlKey
import dev.syncended.sncd.service.encoding.EncodeNumberUseCase
import dev.syncended.sncd.service.encoding.EncodeSecretKeyUseCase

class ExtractShortUrlUseCase(
    private val encodeSecretKey: EncodeSecretKeyUseCase,
    private val encodeNumber: EncodeNumberUseCase
) {

    fun invoke(key: UrlKey): String {
        val secretPart = encodeSecretKey.invoke(key.secretKey)
        val sequencePart = encodeNumber.invoke(key.id.value)
        return "$secretPart$sequencePart"
    }
}