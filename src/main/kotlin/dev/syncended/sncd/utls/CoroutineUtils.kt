@file:Suppress("RedundantSuspendModifier")

package dev.syncended.sncd.utls

import kotlinx.coroutines.CancellationException

suspend inline fun <T> suspendRunCatching(body: () -> T): Result<T> {
    return runCatching { body() }
        .onFailure { if (it is CancellationException) throw it }
}