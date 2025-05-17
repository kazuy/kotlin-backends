package net.kazuy

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import net.kazuy.proto.generated.HelloRequest
import net.kazuy.proto.generated.HelloServiceGrpcKt

fun Application.configureRouting(helloService: HelloServiceGrpcKt.HelloServiceCoroutineStub) {
    routing {
        get("/") {
            val name = call.request.queryParameters["name"] ?: "world"
            val request = HelloRequest.newBuilder().setName(name).build()
            val reply = helloService.sayHello(request)

            call.respondText(reply.message)
        }
    }
}
