package dev.syncended.sncd.web.index

import dev.syncended.kube.components.ui.ButtonType
import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.fillMaxWidth
import dev.syncended.kube.core.component.marginTop
import dev.syncended.kube.core.model.RenderMode
import dev.syncended.kube.dsl.buttonInput
import dev.syncended.kube.dsl.form
import dev.syncended.kube.dsl.text
import dev.syncended.kube.dsl.textInput
import dev.syncended.kube.htmx.modifier.hxPost
import dev.syncended.kube.ktor.core.respondRender
import dev.syncended.kube.styling.Size.rem05
import dev.syncended.sncd.app.inject
import dev.syncended.sncd.model.FormData
import dev.syncended.sncd.service.operation.CreateShortUrlOperation
import io.ktor.server.request.receiveParameters
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

private val createShortUrlOperation by inject<CreateShortUrlOperation>()

fun Routing.postShortener() = post("/shortener") {
    val formInput = call.receiveParameters()
    val url = formInput[FormData.URL]

    if (url == null) {
        call.respondRender(mode = RenderMode.VIEW_ONLY) { shortenerInput() }
    } else {
        createShortUrlOperation(url)
            .onSuccess { call.respondRender(mode = RenderMode.VIEW_ONLY) { shortUrl(it) } }
            .onFailure { call.respondRender(mode = RenderMode.VIEW_ONLY) { shortenerInput() } }
    }
}

fun Layout.shortenerInput() {
    form(
        modifier = Modifier.fillMaxWidth()
            .hxPost("/shortener")
    ) {
        textInput(
            modifier = Modifier.fillMaxWidth(),
            name = FormData.URL
        )
        buttonInput(
            text = "Shorten URL",
            modifier = Modifier.marginTop(rem05),
            type = ButtonType.SUBMIT
        )
    }
}

fun Layout.shortUrl(url: String) {
    text("Your short url is: $url")
}