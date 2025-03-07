package dev.syncended.sncd.web

import dev.syncended.sncd.web.page.getIndex
import io.ktor.server.routing.Routing

fun Routing.webRouting() {
    getIndex()
}