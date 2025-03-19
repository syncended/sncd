package dev.syncended.sncd.converter

import dev.syncended.sncd.model.table.UrlTable
import dev.syncended.sncd.model.url.Url
import dev.syncended.sncd.model.url.toUrlId
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.statements.UpdateBuilder

fun ResultRow.toUrl(): Url = Url(
    id = this[UrlTable.id].toUrlId(),
    secretKey = this[UrlTable.secretKey],
    url = this[UrlTable.url],
)

fun Url.toRecord(builder: UpdateBuilder<Int>) {
    builder[UrlTable.secretKey] = secretKey
    builder[UrlTable.url] = url
}