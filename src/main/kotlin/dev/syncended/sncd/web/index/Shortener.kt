package dev.syncended.sncd.web.index

import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.fillMaxWidth
import dev.syncended.kube.core.component.marginTop
import dev.syncended.kube.dsl.buttonInput
import dev.syncended.kube.dsl.form
import dev.syncended.kube.dsl.textInput
import dev.syncended.kube.styling.Size.rem05
import dev.syncended.sncd.model.FormData
import io.ktor.server.request.receiveParameters
import io.ktor.server.routing.Routing
import io.ktor.server.routing.post

fun Routing.postShortener() = post("/shortener") {
    val formInput = call.receiveParameters()
    val url = formInput[FormData.URL]
}



fun Layout.shortenerInput() {
    form(modifier = Modifier.fillMaxWidth()) {
        textInput(
            modifier = Modifier.fillMaxWidth(),
            name = FormData.URL
        )
        buttonInput(text = "Shorten URL", modifier = Modifier.marginTop(rem05))
    }
}