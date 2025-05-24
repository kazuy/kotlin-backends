plugins {
    alias(libs.plugins.kotlin.jvm)
}

dependencies {
    implementation(project(":stub"))
    runtimeOnly(libs.grpc.netty.shaded)
    implementation(libs.grpc.services)
}
