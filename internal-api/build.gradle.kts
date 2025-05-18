// gRPC Kotlin Codegen Plugin for Protobuf Compiler
// https://github.com/grpc/grpc-kotlin/blob/master/compiler/README.md
val grpc_kotlin_version: String by project
val grpc_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
}

group = "net.kazuy"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":stub"))
    implementation("io.grpc:grpc-kotlin-stub:${grpc_kotlin_version}")
    implementation("io.grpc:grpc-protobuf:${grpc_version}")
    implementation("io.grpc:grpc-services:${grpc_version}")
    runtimeOnly("io.grpc:grpc-netty-shaded:${grpc_version}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
