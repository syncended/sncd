package dev.syncended.sncd.web.component

import dev.syncended.kube.core.component.Layout
import dev.syncended.kube.core.component.Modifier
import dev.syncended.kube.core.component.fillMaxSize
import dev.syncended.kube.core.component.height
import dev.syncended.kube.core.component.maxWidth
import dev.syncended.kube.core.component.paddingHorizontal
import dev.syncended.kube.core.model.Alignment
import dev.syncended.kube.core.model.percent
import dev.syncended.kube.dsl.box
import dev.syncended.kube.dsl.column
import dev.syncended.kube.dsl.space
import dev.syncended.kube.styling.Size.rem1

fun Layout.webpage(body: Layout.() -> Unit) {
    box(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.Vertical.Center,
        horizontalAlignment = Alignment.Horizontal.Center
    ) {
        column(
            modifier = Modifier.fillMaxSize().maxWidth(Sizes.pageSize)
                .paddingHorizontal(rem1),
            alignment = Alignment.Horizontal.Center
        ) {
            space(modifier = Modifier.height(40.percent))
            body()
            space(modifier = Modifier.height(60.percent))
        }
    }
}