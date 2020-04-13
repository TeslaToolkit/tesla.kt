package org.teslatoolkit.example

import org.teslatoolkit.auth.AccountAuthentication
import org.teslatoolkit.http.TeslaHttpClient

suspend fun main(args: Array<String>) {
  val client = TeslaHttpClient.create(
    AccountAuthentication(
      email = args[0],
      password = args[1]
    )
  )

  client.listVehicles().forEach { vehicle ->
    println("Vehicle ${vehicle.vehicleId}:")
    println("  State: ${vehicle.state}")
  }

  client.close()
}
