package dev.syncended.sncd.app

import dev.syncended.sncd.service.encoding.DecodeNumberUseCase
import dev.syncended.sncd.service.encoding.EncodeNumberUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val repositoryModule = module {

}

private val serviceModule = module {
    singleOf(::DecodeNumberUseCase)
    singleOf(::EncodeNumberUseCase)
}

val appModule = module {
    single { CoroutineScope(Dispatchers.IO) }

    includes(repositoryModule, serviceModule)
}