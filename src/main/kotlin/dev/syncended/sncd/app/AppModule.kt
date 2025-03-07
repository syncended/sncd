package dev.syncended.sncd.app

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val appModule = module {
    single { CoroutineScope(Dispatchers.IO) }
}