package org.teslatoolkit.tool.vehicle.show

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.ClimateState
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform
import org.teslatoolkit.tool.vehicle.VehicleSpecificCommand
import org.teslatoolkit.tool.vehicle.printHumanFormat

class ShowVehicleClimateStateCommand : VehicleSpecificCommand(
  name = "climate",
  help = "Show Vehicle Climate State"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    requireVehicleWake()

    val state = getTargetVehicleClimateState()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        ClimateState.serializer(),
        state
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        ClimateState.serializer(),
        state
      ))

      OutputFormat.Human -> state.printHumanFormat()
    }
  }
}
