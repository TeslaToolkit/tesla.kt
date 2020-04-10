package org.teslatoolkit

import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState

interface TeslaClient {
  suspend fun listVehicles(): List<Vehicle>
  suspend fun getVehicle(id: Long): Vehicle
  suspend fun vehicleWakeUp(id: Long): Vehicle
  suspend fun getVehicleState(id: Long): VehicleState
}
