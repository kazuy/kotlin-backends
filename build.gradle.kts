plugins {
    alias(libs.plugins.kotlin.jvm)
}

group = "net.kazuy"
version = "0.0.1"

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    kotlin {
        jvmToolchain(21)
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        testImplementation("io.kotest:kotest-runner-junit5:5.9.1")
        testImplementation("io.kotest:kotest-assertions-core:5.9.1")
    }

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }
}
