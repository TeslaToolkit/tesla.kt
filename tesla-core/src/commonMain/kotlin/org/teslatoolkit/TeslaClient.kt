package org.teslatoolkit

import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState

interface TeslaClient {
  suspend fun listVehicles(): List<Vehicle>
  suspend fun getVehicle(id: Long): Vehicle
  suspend fun getVehicleState(id: Long): VehicleState
  suspend fun getVehicleChargeState(id: Long): ChargeState
  suspend fun getVehicleGuiSettings(id: Long): GuiSettings
  suspend fun getVehicleDriveState(id: Long): DriveState

  suspend fun vehicleWakeUp(id: Long): Vehicle
}
