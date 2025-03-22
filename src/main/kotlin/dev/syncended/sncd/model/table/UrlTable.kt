package dev.syncended.sncd.model.table

import org.jetbrains.exposed.sql.Table

object UrlTable : Table("url") {
    val id = integer(name = "id").autoIncrement()
    val secretKey = integer(name = "secret_key")
    val url = text(name = "url")

    override val primaryKey: PrimaryKey? get() = PrimaryKey(id)
}