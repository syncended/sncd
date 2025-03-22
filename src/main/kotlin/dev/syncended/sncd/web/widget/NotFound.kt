package dev.syncended.sncd.web.widget

import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.dsl.text
import dev.syncended.sncd.web.component.webpage

fun Layout.notFoundPage() = webpage {
    text("404 | Nothing were found")
}