package org.teslatoolkit.java

import kotlinx.coroutines.runBlocking
import org.teslatoolkit.TeslaClient
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
}
