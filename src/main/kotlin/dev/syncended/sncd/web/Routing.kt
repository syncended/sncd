package dev.syncended.sncd.web

import dev.syncended.sncd.web.index.getIndex
import dev.syncended.sncd.web.index.getRedirect
import dev.syncended.sncd.web.index.postShortener
import io.ktor.server.routing.Routing

fun Routing.webRouting() {
    getIndex()
    postShortener()
    getRedirect()
}