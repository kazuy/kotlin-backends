plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

application {
    mainClass = "io.ktor.server.netty.EngineMain"
}

dependencies {
    // Core dependencies
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.config.yaml)

    // Testing
    testImplementation(libs.ktor.server.test.host)

    // gRPC integration
    implementation(project(":stub"))
    runtimeOnly(libs.grpc.netty.shaded)

    // GraphQL integration
    implementation(libs.graphql.kotlin.ktor.server)
}
