package org.teslatoolkit.java

import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.http.KtorHttpService
import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.http.TeslaHttpService
import org.teslatoolkit.java.TeslaClientSync.Companion.create
import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.ClimateState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleConfig
import org.teslatoolkit.model.VehicleData
import org.teslatoolkit.model.VehicleState
import org.teslatoolkit.model.command.CommandRequest
import org.teslatoolkit.model.command.CommandResponse

/**
 * A synchronous Java-friendly version of [TeslaClient].
 * Since [TeslaClient] uses coroutines, we use [runBlocking] to
 * provide a synchronous version of the Tesla API.
 *
 * Use the various [create] methods to build an instance of this client.
 *
 * @param client The [TeslaClient] instance to wrap.
 */
class TeslaClientSync(val client: TeslaClient) : AutoCloseable {
  /**
   * List the vehicles associated with the current Tesla Account.
   *
   * @return A list of [Vehicle] instances.
   */
  fun listVehicles(): List<Vehicle> = runBlocking {
    client.listVehicles()
  }

  /**
   * Get the [Vehicle] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  fun getVehicle(id: Long): Vehicle = runBlocking {
    client.getVehicle(id)
  }

  /**
   * Get the [VehicleState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  fun getVehicleState(id: Long): VehicleState = runBlocking {
    client.getVehicleState(id)
  }

  /**
   * Get the [ChargeState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @return The [VehicleConfig] instance.
   */
  fun getVehicleChargeState(id: Long): ChargeState = runBlocking {
    client.getVehicleChargeState(id)
  }

  /**
   * Get the [VehicleConfig] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @return The [VehicleConfig] instance.
   */
  fun getVehicleConfig(id: Long): VehicleConfig = runBlocking {
    client.getVehicleConfig(id)
  }

  /**
   * Get the [ClimateState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @return The [ClimateState] instance.
   */
  fun getVehicleClimateState(id: Long): ClimateState = runBlocking {
    client.getVehicleClimateState(id)
  }

  /**
   * Get the [DriveState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @return The [DriveState] instance.
   */
  fun getVehicleDriveState(id: Long): DriveState = runBlocking {
    client.getVehicleDriveState(id)
  }

  /**
   * Get the [GuiSettings] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @return The [GuiSettings] instance.
   */
  fun getVehicleGuiSettings(id: Long): GuiSettings = runBlocking {
    client.getVehicleGuiSettings(id)
  }

  /**
   * Get the [VehicleData] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @return The [VehicleData] instance.
   */
  fun getVehicleData(id: Long): VehicleData = runBlocking {
    client.getVehicleData(id)
  }

  /**
   * Sends a [CommandRequest] to the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @param command The [CommandRequest] describing the command.
   * @return The [CommandResponse] to the request.
   */
  fun <T : CommandRequest<T>> sendVehicleCommand(id: Long, command: T): CommandResponse = runBlocking {
    client.sendVehicleCommand(id, command)
  }

  /**
   * Attempt to wake-up the vehicle specified by [id].
   * Check the [Vehicle.state] field after completion to check
   * the status of the vehicle.
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  fun vehicleWakeUp(id: Long): Vehicle = runBlocking {
    client.vehicleWakeUp(id)
  }

  /**
   * Close the internal [TeslaClient] instance.
   */
  override fun close() {
    client.close()
  }

  companion object {
    /**
     * Creates a [TeslaClientSync] by calling [createWithEndpoints] with the
     * given [AuthenticationMethod].
     *
     * @param auth The [AuthenticationMethod] to pass to [createWithEndpoints].
     * @return A built instance of [TeslaClientSync].
     */
    @JvmStatic
    fun create(auth: AuthenticationMethod): TeslaClientSync =
      createWithEndpoints(auth, ApiEndpoints.Standard)

    /**
     * Creates a [TeslaClientSync] by calling [createWithService] with an instance of
     * [KtorHttpService] using the given [endpoints].
     *
     * @param auth The [AuthenticationMethod] to pass to [createWithService].
     * @param endpoints The [ApiEndpoints] to pass to [KtorHttpService].
     * @return A built instance of [TeslaClientSync].
     */
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

    /**
     * Creates a [TeslaClientSync] instance by calling [createWithClient]
     * with an instance of [TeslaHttpClient], injecting [TeslaHttpService] and
     * [AuthenticationMethod] into the [TeslaHttpClient] instance.
     *
     * @param auth The [AuthenticationMethod] to pass to [TeslaHttpClient].
     * @param http The [TeslaHttpService] to pass to [TeslaHttpClient].
     * @return A built instance of [TeslaClientSync].
     */
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

    /**
     * Creates a [TeslaClientSync] instance with the given [TeslaClient] instance.
     *
     * @param client The [TeslaClient] to pass to [TeslaClientSync].
     * @return A built instance of [TeslaClientSync].
     */
    @JvmStatic
    fun createWithClient(client: TeslaClient): TeslaClientSync = TeslaClientSync(
      client = client
    )
  }
}
