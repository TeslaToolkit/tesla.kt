package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.options.flag
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.long
import kotlin.time.ExperimentalTime
import org.teslatoolkit.helper.VehicleWakeHelper
import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState
import org.teslatoolkit.tool.ToolContext

abstract class VehicleSpecificCommand(name: String, help: String) : CliktCommand(name = name, help = help) {
  private val toolContext: ToolContext by requireObject()

  private val id by option(
    "--id",
    help = "Target Vehicle ID"
  ).long().required()

  private val wake by option(
    "--wake",
    help = "Attempt Vehicle Wakeup"
  ).flag()

  @OptIn(ExperimentalTime::class)
  suspend fun possibleVehicleWake(override: Boolean = false): Vehicle =
    if (wake || override) {
      VehicleWakeHelper(toolContext.client!!).wake(id)
    } else {
      getTargetVehicle()
    }

  suspend fun requireVehicleWake() {
    val vehicle = possibleVehicleWake(true)
    if (vehicle.state == "asleep") {
      throw RuntimeException("Failed to wake vehicle.")
    }
  }

  suspend fun getTargetVehicle(): Vehicle =
    toolContext.client!!.getVehicle(id)
  suspend fun getTargetVehicleState(): VehicleState =
    toolContext.client!!.getVehicleState(id)
  suspend fun getTargetVehicleChargeState(): ChargeState =
    toolContext.client!!.getVehicleChargeState(id)
  suspend fun getTargetVehicleGuiSettings(): GuiSettings =
    toolContext.client!!.getVehicleGuiSettings(id)
  suspend fun getTargetVehicleDriveState(): DriveState =
    toolContext.client!!.getVehicleDriveState(id)
}
