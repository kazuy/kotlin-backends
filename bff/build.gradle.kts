val kotlin_version: String by project
val logback_version: String by project

// gRPC Kotlin Codegen Plugin for Protobuf Compiler
// https://github.com/grpc/grpc-kotlin/blob/master/compiler/README.md
val grpc_kotlin_version: String by project
val grpc_version: String by project
val protobuf_kotlin_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("io.ktor.plugin") version "3.1.3"
    id("com.google.protobuf") version "0.9.5"
}

group = "net.kazuy"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-core")
    implementation("io.ktor:ktor-server-config-yaml")

    implementation("io.grpc:grpc-kotlin-stub:${grpc_kotlin_version}")
    implementation("io.grpc:grpc-protobuf:${grpc_version}")
    runtimeOnly("io.grpc:grpc-netty-shaded:${grpc_version}")
    implementation("io.grpc:grpc-services:${grpc_version}")
    implementation("com.google.protobuf:protobuf-kotlin:${protobuf_kotlin_version}")

    testImplementation("io.ktor:ktor-server-test-host")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:$protobuf_kotlin_version"
    }
    plugins {
        create("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:$grpc_version"
        }
        create("grpcKt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:$grpc_kotlin_version:jdk8@jar"
        }
    }
    generateProtoTasks {
        all().forEach {
            it.plugins {
                create("grpc")
                create("grpcKt")
            }
            it.builtins {
                create("kotlin")
            }
        }
    }
}

sourceSets {
    main {
        proto {
            srcDir("$rootDir/proto")
        }
    }
}
