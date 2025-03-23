package dev.syncended.sncd.web.index

import dev.syncended.kube.components.ui.ButtonType
import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.fillMaxWidth
import dev.syncended.kube.core.component.marginRight
import dev.syncended.kube.core.component.marginTop
import dev.syncended.kube.core.component.textSelection
import dev.syncended.kube.core.model.Alignment
import dev.syncended.kube.core.model.TextSelection
import dev.syncended.kube.dsl.box
import dev.syncended.kube.dsl.buttonInput
import dev.syncended.kube.dsl.column
import dev.syncended.kube.dsl.form
import dev.syncended.kube.dsl.row
import dev.syncended.kube.dsl.space
import dev.syncended.kube.dsl.text
import dev.syncended.kube.dsl.textInput
import dev.syncended.kube.htmx.modifier.hxPost
import dev.syncended.kube.ktor.core.respondRenderView
import dev.syncended.kube.styling.Colors
import dev.syncended.kube.styling.Size.rem05
import dev.syncended.kube.styling.Size.rem1
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
    val operationResult = createShortUrlOperation(url)

    call.respondRenderView {
        operationResult
            .onSuccess { shortUrl(it) }
            .onFailure { shortenerInput(it.toString()) }
    }
}

fun Layout.shortenerInput(error: String? = null) {
    form(
        modifier = Modifier.fillMaxWidth()
            .hxPost("/shortener")
    ) {
        textInput(
            modifier = Modifier.fillMaxWidth(),
            name = FormData.URL
        )
        error?.let { text(error, color = Colors.red) }
        buttonInput(
            text = "Shorten URL",
            modifier = Modifier.marginTop(rem05),
            type = ButtonType.SUBMIT
        )
    }
}

fun Layout.shortUrl(url: String) = column(modifier = Modifier.fillMaxWidth(), alignment = Alignment.Horizontal.Center) {
    row {
        text("Short url:", modifier = Modifier.marginRight(rem1))
        text(url, modifier = Modifier.textSelection(TextSelection.ALL))
    }
}