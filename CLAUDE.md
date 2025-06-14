# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a Kotlin microservices backend system with two main services:
- **BFF (Backend for Frontend)**: Ktor-based GraphQL server on port 8080
- **Internal API**: gRPC server on port 50051 using Protocol Buffers

The BFF translates GraphQL queries into gRPC calls to the Internal API, which handles business logic.

## Architecture

### Module Structure
- `bff/`: GraphQL server using Ktor and graphql-kotlin
- `internal-api/`: gRPC server with business logic
- `protos/`: Protocol Buffer definitions (.proto files)
- `stub/`: Generated gRPC client/server stubs from protos
- `docker/`: Docker configurations for both services

### Key Dependencies
- Kotlin 2.1.10 with JVM 21
- Ktor 3.1.3 for web framework
- GraphQL Kotlin 9.0.0-alpha.8
- gRPC Kotlin 1.4.3
- Exposed 0.61.0 for database ORM
- Kotest 5.9.1 for testing

### Service Communication
1. BFF receives GraphQL queries
2. Configures gRPC channel to Internal API (environment configurable)
3. Creates `HelloServiceCoroutineStub` for async gRPC calls
4. Internal API uses `HelloServiceCoroutineImplBase` for implementation

## Development Commands

### Building and Running
```bash
# Build all modules
./gradlew build

# Run BFF service (port 8080)
./gradlew :bff:run

# Run Internal API service (port 50051)
./gradlew :internal-api:run

# Build shadow JARs for deployment
./gradlew :bff:shadowJar
./gradlew :internal-api:shadowJar
```

### Testing
```bash
# Run all tests
./gradlew test

# Run specific module tests
./gradlew :internal-api:test
./gradlew :bff:test
```

### Protocol Buffers
- Proto files are in `protos/src/main/proto/`
- Stubs are auto-generated in `stub/` module during build
- Generated classes are in package `net.kazuy.proto.generated`

### Docker Development
```bash
# Development environment (database only)
docker compose -p kotlin-backends -f ./docker/docker-compose.dev.yml up -d

# Build and run all services
docker compose -p kotlin-backends -f ./docker/docker-compose.build.yml build
docker compose -p kotlin-backends -f ./docker/docker-compose.build.yml up -d
```

## Environment Configuration

### BFF Service
- `INTERNAL_API_HOST`: gRPC server host (default: localhost)
- `INTERNAL_API_PORT`: gRPC server port (default: 50051)

### Internal API
- `PORT`: gRPC server port (default: 50051)

## Testing Access
- GraphQL endpoint: http://localhost:8080/graphql
- GraphiQL interface: http://localhost:8080/graphiql

## Code Generation Notes
- gRPC stubs are generated from proto files using protobuf plugin
- Generated code includes both Java and Kotlin variants
- Kotlin coroutine stubs are used for async operations

## Commit Message Guidelines

Follow the conventional commit format used in this project:

```
<type>: <description>

🤖 Generated with [Claude Code](https://claude.ai/code)

Co-Authored-By: Claude <noreply@anthropic.com>
```

### Commit Types
- `feat:` - New features
- `fix:` - Bug fixes
- `refactor:` - Code restructuring without changing functionality

### Examples from Project History
- `feat: add postgres container`
- `feat: add hello handler test`
- `fix: update`
- `fix: refactor`