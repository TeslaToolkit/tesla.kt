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
import org.teslatoolkit.model.ClimateState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleConfig
import org.teslatoolkit.model.VehicleState
import org.teslatoolkit.tool.ToolContext

abstract class VehicleSpecificCommand(name: String, help: String) : CliktCommand(name = name, help = help) {
  private val toolContext: ToolContext by requireObject()

  val targetVehicleId by option(
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
      VehicleWakeHelper(toolContext.client!!).wake(targetVehicleId)
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
    toolContext.client!!.getVehicle(targetVehicleId)
  suspend fun getTargetVehicleConfig(): VehicleConfig =
    toolContext.client!!.getVehicleConfig(targetVehicleId)
  suspend fun getTargetVehicleState(): VehicleState =
    toolContext.client!!.getVehicleState(targetVehicleId)
  suspend fun getTargetVehicleChargeState(): ChargeState =
    toolContext.client!!.getVehicleChargeState(targetVehicleId)
  suspend fun getTargetVehicleClimateState(): ClimateState =
    toolContext.client!!.getVehicleClimateState(targetVehicleId)
  suspend fun getTargetVehicleGuiSettings(): GuiSettings =
    toolContext.client!!.getVehicleGuiSettings(targetVehicleId)
  suspend fun getTargetVehicleDriveState(): DriveState =
    toolContext.client!!.getVehicleDriveState(targetVehicleId)
}
