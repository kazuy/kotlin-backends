package net.kazuy

import io.grpc.Server
import io.grpc.ServerBuilder
import io.grpc.protobuf.services.ProtoReflectionServiceV1

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 50051
    val server = InternalApiServer(port)
    server.start()
    server.blockUntilShutdown()
}

class InternalApiServer(private val port: Int) {
    val server: Server = ServerBuilder
        .forPort(port)
        .addService(HelloHandler())
        .addService(ProtoReflectionServiceV1.newInstance())
        .build()

    fun start() {
        server.start()
        println("gRPC server started, listening on port $port")
        Runtime.getRuntime().addShutdownHook(
            Thread {
                println("*** Shutting down gRPC server since JVM is shutting down")
                this@InternalApiServer.stop()
                println("*** Server shut down")
            }
        )
    }

    private fun stop() {
        server.shutdown()
    }

    fun blockUntilShutdown() {
        server.awaitTermination()
    }
}
