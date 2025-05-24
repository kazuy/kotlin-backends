package net.kazuy

import net.kazuy.proto.generated.HelloRequest
import net.kazuy.proto.generated.HelloResponse
import net.kazuy.proto.generated.HelloServiceGrpcKt
import net.kazuy.proto.generated.helloResponse

class HelloHandler : HelloServiceGrpcKt.HelloServiceCoroutineImplBase() {
    override suspend fun sayHello(request: HelloRequest): HelloResponse =
        helloResponse {
            message = "Hello ${request.name}"
        }
}
