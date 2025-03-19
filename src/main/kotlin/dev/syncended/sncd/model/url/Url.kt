package dev.syncended.sncd.model.url

sealed class UrlId {
    abstract val value: Int

    data class Numeric(override val value: Int) : UrlId()
    data object NewId : UrlId() {
        override val value: Int = -1
    }
}

data class Url(
    val id: UrlId,
    val secretKey: Int,
    val url: String
)

fun Int.toUrlId(): UrlId = UrlId.Numeric(this)
