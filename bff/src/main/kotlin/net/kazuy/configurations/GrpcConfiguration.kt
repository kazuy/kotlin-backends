package net.kazuy.configurations

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationStopped

data class GrpcConfiguration(
    val channel: ManagedChannel
)

fun Application.configureGrpc(): GrpcConfiguration {
    val port = System.getenv("INTERNAL_API_PORT")?.toInt() ?: 50051
    val channel = ManagedChannelBuilder
        .forAddress("localhost", port)
        .usePlaintext()
        .build()

    monitor.subscribe(ApplicationStopped) { application ->
        application.environment.log.info("gRPC server stopped")
        channel.shutdown()
    }

    return GrpcConfiguration(channel)
}
