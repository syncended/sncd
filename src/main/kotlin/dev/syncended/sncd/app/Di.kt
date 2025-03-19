package dev.syncended.sncd.app

import dev.syncended.sncd.repository.url.UrlRepository
import dev.syncended.sncd.service.encoding.DecodeNumberUseCase
import dev.syncended.sncd.service.encoding.EncodeNumberUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.component.KoinComponent
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val repositoryModule = module {
    singleOf(::UrlRepository)
}

private val serviceModule = module {
    singleOf(::DecodeNumberUseCase)
    singleOf(::EncodeNumberUseCase)
}

val appModule = module {
    single { CoroutineScope(Dispatchers.IO) }
    single { loadConfiguration() }

    includes(repositoryModule, serviceModule)
}

val staticComponent = object : KoinComponent {}

inline fun <reified T : Any> inject(): Lazy<T> {
    return staticComponent.getKoin().inject<T>()
}
