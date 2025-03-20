package dev.syncended.sncd.service.url

import dev.syncended.sncd.app.Configuration
import dev.syncended.sncd.model.url.UrlKey
import dev.syncended.sncd.service.encoding.EncodeNumberUseCase
import dev.syncended.sncd.service.encoding.EncodeSecretKeyUseCase

class ExtractShortUrlUseCase(
    private val encodeSecretKeyUseCase: EncodeSecretKeyUseCase,
    private val encodeNumberUseCase: EncodeNumberUseCase,
    private val configuration: Configuration
) {

    operator fun invoke(key: UrlKey): String {
        val secretPart = encodeSecretKeyUseCase(key.secretKey)
        val sequencePart = encodeNumberUseCase(key.id.value)
        return "${configuration.host}$secretPart$sequencePart"
    }
}