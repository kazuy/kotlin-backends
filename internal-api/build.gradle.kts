import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.shadow)
}

dependencies {
    implementation(project(":stub"))
    runtimeOnly(libs.grpc.netty.shaded)
    implementation(libs.grpc.services)
}

tasks.named<ShadowJar>("shadowJar") {
    manifest {
        attributes["Main-Class"] = "net.kazuy.MainKt"
    }
}
