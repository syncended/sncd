package dev.syncended.sncd.web.component

import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.fillMaxSize
import dev.syncended.kube.core.model.Alignment
import dev.syncended.kube.dsl.box

fun Layout.webpage(body: Layout.() -> Unit) {
    box(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Vertical.Center,
        horizontalAlignment = Alignment.Horizontal.Center
    ) {
        
        body()
    }
}