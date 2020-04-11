package org.teslatoolkit.java

import io.ktor.client.HttpClient
import java.lang.AutoCloseable
import kotlinx.coroutines.runBlocking
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.http.KtorHttpService
import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.http.TeslaHttpService
import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState

class TeslaClientSync(val client: TeslaClient) : AutoCloseable {
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

  override fun close() {
    client.close()
  }

  companion object {
    @JvmStatic
    fun create(auth: AuthenticationMethod): TeslaClientSync =
      createWithEndpoints(auth, ApiEndpoints.Standard)

    @JvmStatic
    fun createWithEndpoints(
      auth: AuthenticationMethod,
      endpoints: ApiEndpoints
    ): TeslaClientSync = createWithService(
      auth,
      http = KtorHttpService(
        HttpClient(),
        endpoints
      )
    )

    @JvmStatic
    fun createWithService(
      auth: AuthenticationMethod,
      http: TeslaHttpService
    ): TeslaClientSync = createWithClient(
      client = TeslaHttpClient(
        http = http,
        auth = auth
      )
    )

    @JvmStatic
    fun createWithClient(client: TeslaClient): TeslaClientSync = TeslaClientSync(
      client = client
    )
  }
}
