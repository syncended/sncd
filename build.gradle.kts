plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "dev.syncended.sncd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlin.coroutines.core)

    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.koin.core)
    implementation(libs.kube.ktor)

    implementation(libs.exposed.core)
    implementation(libs.exposed.jdbc)
    implementation(libs.hikari)
    runtimeClasspath(libs.h2)
    runtimeClasspath(libs.postgresql)
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}