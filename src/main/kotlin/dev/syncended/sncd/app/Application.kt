package dev.syncended.sncd.app

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import dev.syncended.sncd.model.table.UrlTable
import dev.syncended.sncd.web.webRouting
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.koin.core.context.startKoin

fun main() {
    startKoin { modules(appModule) }
    bootstrapDatabase()
    runWebserver()
}

private fun bootstrapDatabase() {
    val configuration by inject<Configuration>()
    val config = HikariConfig().apply {
        jdbcUrl = configuration.database.jdbcUrl
        driverClassName = configuration.database.driverName
        maximumPoolSize = 32
    }
    val datasource = HikariDataSource(config)
    Database.connect(datasource)
    if (configuration.isDebug) {
        transaction { SchemaUtils.createStatements(UrlTable) }
    }
}

private fun runWebserver() = embeddedServer(
    factory = Netty,
    host = "0.0.0.0",
    port = 80,
) {
    routing { webRouting() }
}.start(wait = true)