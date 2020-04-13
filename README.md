# Tesla for Kotlin

[![Build Status](https://travis-ci.org/TeslaToolkit/tesla.kt.svg?branch=master)](https://travis-ci.org/TeslaToolkit/tesla.kt)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.teslatoolkit/tesla-core/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.teslatoolkit/tesla-core)

A multi-platform Tesla API Client Library for Kotlin and Java.

```gradle
dependencies {
  implementation("org.teslatoolkit:tesla-core:0.1.0")
}
```

## Features

- Authentication
  - Email/Password
  - OAuth2 Token
- Read Access
  - Vehicles on Account
  - Climate State
  - Drive State
  - Media State
  - Charge State
  - Vehicle Configuration
  - GUI Settings

## Examples

### Kotlin

```kotlin
suspend fun main() {
  val client = TeslaHttpClient.create(
    AccountAuthentication(
      email = "my.tesla.email@gmail.com",
      password = "MySecurePassword"
    )
  )

  client.listVehicles().forEach { vehicle ->
    println("Vehicle ${vehicle.vehicleId}:")
    println("  State: ${vehicle.state}")
  }

  client.close()
}
```

### Java

```java
class ExampleMain {
  public static void main(String[] args){
    AuthenticationMethod auth = new AccountAuthentication(args[0], args[1]);
    try (TeslaClientSync client = TeslaClientSync.create(auth)) {
      for (Vehicle vehicle : client.listVehicles()) {
        System.out.println("Vehicle " + vehicle.getVehicleId());
        System.out.println("  State: " + vehicle.getState());
      }
    }
  }
}
```
