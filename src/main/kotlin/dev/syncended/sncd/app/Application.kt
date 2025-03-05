package dev.syncended.sncd.app

import dev.syncended.kube.dsl.text
import dev.syncended.kube.ktor.core.respondRender
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import org.koin.core.context.startKoin
import org.koin.dsl.module

private val appModule = module {

}

fun main() {
    startKoin { modules(appModule) }
    embeddedServer(
        factory = Netty,
        host = "0.0.0.0",
        port = 80,
    ) {
        routing { get("/") { call.respondRender { text("Hello sncd") } } }
    }.start(wait = true)
}