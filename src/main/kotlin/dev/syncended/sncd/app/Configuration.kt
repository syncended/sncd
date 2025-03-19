package dev.syncended.sncd.app

data class Configuration(
    val isDebug: Boolean,
    val database: DbConfig
)

data class DbConfig(
    val driverName: String,
    val jdbcUrl: String
)

fun loadConfiguration(): Configuration = Configuration(
    isDebug = propertyOrDefault("production", "").isBlank(),
    database = loadDbConfig()
)

private fun loadDbConfig(): DbConfig = DbConfig(
    driverName = propertyOrDefault(
        propertyName = "database_driver",
        default = "org.h2.Driver"
    ),
    jdbcUrl = propertyOrDefault(
        propertyName = "database_url",
        default = "jdbc:h2:mem:inmem_db"
    )
)

private fun propertyOrDefault(propertyName: String, default: String): String {
    return System.getenv()[propertyName] ?: default
}