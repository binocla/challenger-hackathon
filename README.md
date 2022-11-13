# challenger-hackathon Project

This project uses Quarkus Framework
Currently, it's being exposed on http://51.250.12.148:8080/q/swagger-ui/#/

## Running the application

```shell script
./mvnw compile quarkus:dev
```

## Packaging the application

Supports GraalVM Native Executable build! Yay!

```shell script
./mvnw clean package -Pnative -Dquarkus.native.container-build=true
```

## Tech Stack:
- Quarkus
- RESTEasy Reactive
- Hibernate Reactive with Panache
- PostgreSQL
- SmallRye Health
- SmallRye OpenAPI
- SmallRye Fault Tolerance
- GraalVM Native Executable
- Docker
- SmallRye Metrics

