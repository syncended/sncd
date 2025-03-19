package dev.syncended.sncd.utls

import org.jetbrains.exposed.sql.Transaction
import org.jetbrains.exposed.sql.transactions.experimental.suspendedTransactionAsync

suspend fun <T> suspendReadOnlyTransaction(body: Transaction.() -> T) =
    safeSuspendTransaction(readOnly = true, body = body)

suspend fun <T> safeSuspendTransaction(readOnly: Boolean = false, body: Transaction.() -> T): Result<T> =
    suspendRunCatching { suspendedTransactionAsync(statement = body, readOnly = readOnly).await() }