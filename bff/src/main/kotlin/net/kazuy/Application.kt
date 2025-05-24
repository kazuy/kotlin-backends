package net.kazuy

import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import net.kazuy.configurations.configureGraphQL
import net.kazuy.configurations.configureGrpc
import net.kazuy.proto.generated.HelloServiceGrpcKt

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    val grpcConfiguration = configureGrpc()
    val helloService = HelloServiceGrpcKt.HelloServiceCoroutineStub(grpcConfiguration.channel)

    configureGraphQL(helloService)
    configureRouting()
}
