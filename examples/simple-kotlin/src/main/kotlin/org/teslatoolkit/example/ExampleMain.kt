package org.teslatoolkit.example

import io.ktor.client.HttpClient
import org.teslatoolkit.auth.AccountAuthentication
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.http.KtorHttpService
import org.teslatoolkit.http.TeslaHttpClient

suspend fun main(args: Array<String>) {
  val auth: AuthenticationMethod = AccountAuthentication(args[0], args[1])
  val client = TeslaHttpClient(
    http = KtorHttpService(
      client = HttpClient(),
      endpoints = ApiEndpoints.Standard
    ),
    auth = auth
  )

  client.listVehicles().forEach { vehicle ->
    println("Vehicle ${vehicle.vehicleId}:")
    println("  State: ${vehicle.state}")
  }

  client.close()
}
