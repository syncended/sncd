package dev.syncended.sncd.web.index

import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.marginBottom
import dev.syncended.kube.dsl.text
import dev.syncended.kube.ktor.core.respondRender
import dev.syncended.kube.styling.Size.rem05
import dev.syncended.sncd.web.component.Sizes
import dev.syncended.sncd.web.component.webpage
import io.ktor.server.routing.Routing
import io.ktor.server.routing.get

fun Routing.getIndex() = get("/") {
    call.respondRender { indexPage() }
}

private fun Layout.indexPage() = webpage {
    text(
        text = "sncd",
        modifier = Modifier.marginBottom(rem05),
        textSize = Sizes.headingText
    )
    shortenerInput()
}