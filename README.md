# challenger-hackathon Project

This project uses Quarkus Framework

## Running the application

```shell script
./mvnw compile quarkus:dev
```

## Packaging the application

Supports GraalVM Native Executable build! Yay!

```shell script
./mvnw clean package -Pnative -Dquarkus.native.container-build=true
```

