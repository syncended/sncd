package dev.syncended.sncd.model

object Encoding {
    const val ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
    const val BASE = ALPHABET.length
    const val ZERO_CHAR = ALPHABET[0]
    val CHAR_TO_INDEX = ALPHABET.mapIndexed { index, char -> char to index }.toMap()

    const val SECRET_KEY_LENGTH = 3
}