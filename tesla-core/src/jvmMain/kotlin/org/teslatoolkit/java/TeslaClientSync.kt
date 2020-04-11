package org.teslatoolkit.java

import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.http.KtorHttpService
import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState

class TeslaClientSync(val client: TeslaClient) {
  fun listVehicles(): List<Vehicle> = runBlocking {
    client.listVehicles()
  }

  fun getVehicle(id: Long): Vehicle = runBlocking {
    client.getVehicle(id)
  }

  fun getVehicleState(id: Long): VehicleState = runBlocking {
    client.getVehicleState(id)
  }

  fun getVehicleChargeState(id: Long): ChargeState = runBlocking {
    client.getVehicleChargeState(id)
  }

  fun getVehicleDriveState(id: Long): DriveState = runBlocking {
    client.getVehicleDriveState(id)
  }

  fun getVehicleGuiSettings(id: Long): GuiSettings = runBlocking {
    client.getVehicleGuiSettings(id)
  }

  fun vehicleWakeUp(id: Long): Vehicle = runBlocking {
    client.vehicleWakeUp(id)
  }

  companion object {
    @JvmStatic
    fun create(auth: AuthenticationMethod): TeslaClientSync = TeslaClientSync(
      client = TeslaHttpClient(
        http = KtorHttpService(
          HttpClient(),
          ApiEndpoints.Standard
        ),
        auth = auth
      )
    )
  }
}
