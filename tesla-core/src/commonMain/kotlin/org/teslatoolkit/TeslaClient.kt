package org.teslatoolkit

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
 * A Tesla API Client using coroutines.
 */
interface TeslaClient {
  /**
   * List the vehicles associated with the current Tesla Account.
   *
   * @return A list of [Vehicle] instances.
   */
  suspend fun listVehicles(): List<Vehicle>

  /**
   * Get the [Vehicle] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicle(id: Long): Vehicle

  /**
   * Get the [VehicleState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleState(id: Long): VehicleState

  /**
   * Get the [ChargeState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleChargeState(id: Long): ChargeState

  /**
   * Get the [ClimateState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleClimateState(id: Long): ClimateState

  /**
   * Get the [DriveState] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleDriveState(id: Long): DriveState

  /**
   * Get the [GuiSettings] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleGuiSettings(id: Long): GuiSettings

  /**
   * Get the [VehicleConfig] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleConfig(id: Long): VehicleConfig

  /**
   * Get the [VehicleData] for the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun getVehicleData(id: Long): VehicleData

  /**
   * Sends a [CommandRequest] to the vehicle specified by [id].
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   * @param command The [CommandRequest] describing the command.
   * @return The [CommandResponse] to the request.
   */
  suspend fun <T : CommandRequest<T>> sendVehicleCommand(id: Long, command: T): CommandResponse

  /**
   * Attempt to wake-up the vehicle specified by [id].
   * Check the [Vehicle.state] field after completion to check
   * the status of the vehicle.
   *
   * @param id The global vehicle ID, same as [Vehicle.globalId].
   */
  suspend fun vehicleWakeUp(id: Long): Vehicle

  /**
   * Closes any internal resources used by this client.
   */
  fun close()
}
