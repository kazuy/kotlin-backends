package net.kazuy.configurations

import com.expediagroup.graphql.server.ktor.GraphQL
import io.ktor.server.application.*
import io.ktor.server.application.install
import net.kazuy.graphql.schema.queries.HelloQuery
import net.kazuy.proto.generated.HelloServiceGrpcKt

fun Application.configureGraphQL(helloService: HelloServiceGrpcKt.HelloServiceCoroutineStub) {
    install(GraphQL) {
        schema {
            packages = listOf("net.kazuy.graphql")
            queries = listOf(
                HelloQuery(helloService)
            )
        }
    }
}
