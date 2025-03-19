package dev.syncended.sncd.service.url

import dev.syncended.sncd.model.url.Url
import dev.syncended.sncd.model.url.UrlId

class GenerateUrlUseCase(
    private val generateSecretKey: GenerateSecretKeyUseCase
) {

    fun invoke(url: String): Url {
        val secretKey = generateSecretKey.invoke()
        return Url(
            id = UrlId.NewId,
            secretKey = secretKey,
            url = url
        )
    }
}