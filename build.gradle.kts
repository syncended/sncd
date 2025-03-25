import java.nio.file.Files
import java.nio.file.StandardCopyOption

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
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

tasks.withType<Jar> {
    doLast {
        val arch = archiveFile.get().asFile
        val serviceFile = File(arch.parentFile, "service.jar")
        Files.copy(
            arch.toPath(),
            serviceFile.toPath(),
            StandardCopyOption.REPLACE_EXISTING
        )
    }
}

kotlin {
    jvmToolchain(21)
}