package dev.syncended.sncd.repository.url

import dev.syncended.sncd.converter.toRecord
import dev.syncended.sncd.converter.toUrl
import dev.syncended.sncd.model.exception.UrlNotFoundException
import dev.syncended.sncd.model.table.UrlTable
import dev.syncended.sncd.model.url.Url
import dev.syncended.sncd.model.url.UrlKey
import dev.syncended.sncd.model.url.toUrlId
import dev.syncended.sncd.utls.safeSuspendTransaction
import dev.syncended.sncd.utls.suspendReadOnlyTransaction
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insertReturning
import org.jetbrains.exposed.sql.selectAll

class UrlRepository {

    suspend fun find(urlKey: UrlKey): Result<Url> = suspendReadOnlyTransaction {
        UrlTable.selectAll()
            .where {
                val sameId = UrlTable.id eq urlKey.id.value
                val sameSecretKey = UrlTable.secretKey eq urlKey.secretKey
                sameId and sameSecretKey
            }.firstOrNull()
            ?.toUrl()
            ?: throw UrlNotFoundException()
    }

    suspend fun insert(url: Url): Result<UrlKey> = safeSuspendTransaction {
        UrlTable.insertReturning(listOf(UrlTable.id)) { url.toRecord(it) }
            .first()[UrlTable.id]
            .toUrlId()
            .let { UrlKey(id = it, secretKey = url.secretKey) }
    }
}