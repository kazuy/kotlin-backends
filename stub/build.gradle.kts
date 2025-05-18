// gRPC Kotlin Codegen Plugin for Protobuf Compiler
// https://github.com/grpc/grpc-kotlin/blob/master/compiler/README.md
val grpc_kotlin_version: String by project
val grpc_version: String by project
val protobuf_kotlin_version: String by project

plugins {
    kotlin("jvm") version "2.1.10"
    id("com.google.protobuf") version "0.9.5"
}

repositories {
    mavenCentral()
}

dependencies {
    protobuf(project(":protos"))

    implementation("io.grpc:grpc-kotlin-stub:$grpc_kotlin_version")
    implementation("io.grpc:grpc-protobuf:$grpc_version")
    implementation("com.google.protobuf:protobuf-kotlin:$protobuf_kotlin_version")
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
