package dev.syncended.sncd.app

import dev.syncended.sncd.web.webRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import org.koin.core.context.startKoin

fun main() {
    startKoin { modules(appModule) }
    embeddedServer(
        factory = Netty,
        host = "0.0.0.0",
        port = 80,
    ) {
        routing { webRouting() }
    }.start(wait = true)
}