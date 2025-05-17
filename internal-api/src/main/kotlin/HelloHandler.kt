package net.kazuy

import net.kazuy.proto.generated.HelloReply
import net.kazuy.proto.generated.HelloRequest
import net.kazuy.proto.generated.HelloServiceGrpcKt
import net.kazuy.proto.generated.helloReply

class HelloHandler : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest): HelloReply =
        helloReply {
            message = "Hello ${request.name}"
        }
}
