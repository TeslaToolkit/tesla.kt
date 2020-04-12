package org.teslatoolkit.tool.vehicle.show

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform
import org.teslatoolkit.tool.vehicle.VehicleSpecificCommand
import org.teslatoolkit.tool.vehicle.printHumanFormat

class ShowVehicleChargeStateCommand : VehicleSpecificCommand(
  name = "charge",
  help = "Show Vehicle Charge State"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    requireVehicleWake()

    val state = getTargetVehicleChargeState()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        ChargeState.serializer(),
        state
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        ChargeState.serializer(),
        state
      ))

      OutputFormat.Human -> state.printHumanFormat()
    }
  }
}
