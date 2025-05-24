package net.kazuy

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import net.kazuy.proto.generated.helloRequest

class HelloHandlerTest : FunSpec({
    val handler = HelloHandler()

    test("sayHello should return greeting message with the requested name") {
        // Arrange
        val request = helloRequest {
            name = "Kotlin"
        }

        // Act
        val response = handler.sayHello(request)

        // Assert
        response.message shouldBe "Hello Kotlin"
    }

    test("sayHello should handle empty name") {
        // Arrange
        val request = helloRequest {
            name = ""
        }

        // Act
        val response = handler.sayHello(request)

        // Assert
        response.message shouldBe "Hello "
    }

    test("sayHello should handle special characters in name") {
        // Arrange
        val request = helloRequest {
            name = "こんにちは！"
        }

        // Act
        val response = handler.sayHello(request)

        // Assert
        response.message shouldBe "Hello こんにちは！"
    }
})
