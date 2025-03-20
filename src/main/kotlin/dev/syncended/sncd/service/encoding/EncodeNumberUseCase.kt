package dev.syncended.sncd.service.encoding

import dev.syncended.sncd.model.Encoding

class EncodeNumberUseCase {

    operator fun invoke(number: Int) = buildString {
        var value = number
        while (value != 0) {
            val charIndex = value % Encoding.BASE
            append(Encoding.ALPHABET[charIndex])
            value = value / Encoding.BASE
        }
    }.reversed()
}