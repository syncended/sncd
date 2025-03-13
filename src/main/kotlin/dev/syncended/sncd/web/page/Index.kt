package dev.syncended.sncd.web.page

import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.fillMaxWidth
import dev.syncended.kube.dsl.form
import dev.syncended.kube.dsl.text
import dev.syncended.kube.dsl.textInput
import dev.syncended.kube.ktor.core.respondRender
import dev.syncended.sncd.web.component.webpage
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

fun Routing.getIndex() = get("/") {
    call.respondRender { indexPage() }
}

private fun Layout.indexPage() = webpage {
    text("sncd")
    form(modifier = Modifier.fillMaxWidth()) {
        textInput(modifier = Modifier.fillMaxWidth())
    }
}