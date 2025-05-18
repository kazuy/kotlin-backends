plugins {
    alias(libs.plugins.kotlin.jvm)
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":stub"))
    runtimeOnly(libs.grpc.netty.shaded)
    implementation(libs.grpc.services)

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
