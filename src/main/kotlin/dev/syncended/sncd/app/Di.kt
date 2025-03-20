package dev.syncended.sncd.app

import dev.syncended.sncd.repository.url.UrlRepository
import dev.syncended.sncd.service.encoding.DecodeNumberUseCase
import dev.syncended.sncd.service.encoding.EncodeNumberUseCase
import dev.syncended.sncd.service.encoding.EncodeSecretKeyUseCase
import dev.syncended.sncd.service.operation.CreateShortUrlOperation
import dev.syncended.sncd.service.url.ExtractShortUrlUseCase
import dev.syncended.sncd.service.generator.GenerateSecretKeyUseCase
import dev.syncended.sncd.service.url.GenerateUrlUseCase
import dev.syncended.sncd.service.validation.ValidateUrlUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import kotlin.random.Random

private val repositoryModule = module {
    singleOf(::UrlRepository)
}

private val serviceModule = module {
    singleOf(::DecodeNumberUseCase)
    singleOf(::EncodeNumberUseCase)
    singleOf(::EncodeSecretKeyUseCase)

    singleOf(::GenerateSecretKeyUseCase)

    singleOf(::ExtractShortUrlUseCase)
    singleOf(::GenerateUrlUseCase)

    singleOf(::ValidateUrlUseCase)

    singleOf(::CreateShortUrlOperation)
}

val appModule = module {
    single { CoroutineScope(Dispatchers.IO) }
    single { loadConfiguration() }
    single { Random(System.currentTimeMillis()) }

    includes(repositoryModule, serviceModule)
}

val staticComponent = object : KoinComponent {}

inline fun <reified T : Any> inject(): Lazy<T> {
    return staticComponent.getKoin().inject<T>()
}
