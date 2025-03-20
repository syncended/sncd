package dev.syncended.sncd.service.generator

import kotlin.random.Random

class GenerateSecretKeyUseCase(
    private val random: Random
) {

    operator fun invoke(): Int = random.nextInt(MIN_RANDOM_KEY, MAX_RANDOM_KEY)

    private companion object {
        const val MIN_RANDOM_KEY = 0
        const val MAX_RANDOM_KEY = 238328
    }
}