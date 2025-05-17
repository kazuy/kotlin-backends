package net.kazuy

import io.grpc.ManagedChannelBuilder
import io.ktor.server.application.*
import net.kazuy.proto.generated.HelloServiceGrpcKt

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    val port = System.getenv("INTERNAL_API_PORT")?.toInt() ?: 50051
    val channel = ManagedChannelBuilder
        .forAddress("localhost", port)
        .usePlaintext()
        .build()
    val helloService = HelloServiceGrpcKt.HelloServiceCoroutineStub(channel)

    monitor.subscribe(ApplicationStopped) { application ->
        application.environment.log.info("Application stopped")
        channel.shutdown()
    }

    configureRouting(helloService)
}
