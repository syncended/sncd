package dev.syncended.sncd.service.url

import dev.syncended.sncd.model.url.Url
import dev.syncended.sncd.model.url.UrlId
import dev.syncended.sncd.service.generator.GenerateSecretKeyUseCase

class GenerateUrlUseCase(
    private val generateSecretKeyUseCase: GenerateSecretKeyUseCase
) {

    operator fun invoke(url: String): Url {
        val secretKey = generateSecretKeyUseCase()
        return Url(
            id = UrlId.NewId,
            secretKey = secretKey,
            url = url
        )
    }
}