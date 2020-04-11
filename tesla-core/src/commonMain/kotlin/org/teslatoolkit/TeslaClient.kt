package org.teslatoolkit

import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState

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
