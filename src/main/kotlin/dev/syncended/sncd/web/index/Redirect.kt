package dev.syncended.sncd.web.index

import dev.syncended.kube.ktor.core.respondRender
import dev.syncended.sncd.app.Configuration
import dev.syncended.sncd.app.inject
import dev.syncended.sncd.service.operation.GetRedirectUrlOperation
import dev.syncended.sncd.web.widget.notFoundPage
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

private val getRedirectUrlOperation by inject<GetRedirectUrlOperation>()
private val redirectStatusCode by lazy {
    val config by inject<Configuration>()
    if (config.isDebug) HttpStatusCode.TemporaryRedirect else HttpStatusCode.PermanentRedirect
}

fun Routing.getRedirect() = get("/{path}") {
    val path = call.pathParameters["path"]
    getRedirectUrlOperation(path)
        .onSuccess {
            call.response.headers.append("Location", it)
            call.respond(redirectStatusCode)
        }
        .onFailure {
            call.respondRender { notFoundPage() }
        }
}
