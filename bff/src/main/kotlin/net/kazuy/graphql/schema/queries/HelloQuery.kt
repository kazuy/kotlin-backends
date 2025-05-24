package net.kazuy.graphql.schema.queries

import com.expediagroup.graphql.server.operations.Query
import net.kazuy.graphql.schema.models.Hello
import net.kazuy.proto.generated.HelloRequest
import net.kazuy.proto.generated.HelloServiceGrpcKt

class HelloQuery(
    private val helloService: HelloServiceGrpcKt.HelloServiceCoroutineStub
) : Query {
    suspend fun hello(name: String): Hello {
        val request = HelloRequest.newBuilder().setName(name).build()
        val reply = helloService.sayHello(request)

        return Hello(reply.message)
    }
}
