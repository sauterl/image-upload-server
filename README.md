# Image Upload Server

Very simple project to upload images through multipart forms to a server.
Uses [ktor](https://ktor.io/).

## Prerequisites

This is a Kotlin / JVM project and thus requires a suitable JDK for Kotlin +1.8

## Setup

Requires little to no setup with sensible defaults.

To build the project, use the following gradle task:

```shell
./gradlew buildFatJar
```

## Run

To run the fat jar, use

```shell
java -jar build/libs/ius-all.jar
```
