package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.VehicleState
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform

class ShowVehicleStateCommand : VehicleSpecificCommand(
  name = "show-state",
  help = "Show Vehicle State"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    requireVehicleWake()

    val state = getTargetVehicleState()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        VehicleState.serializer(),
        state
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        VehicleState.serializer(),
        state
      ))

      OutputFormat.Human -> state.printHumanFormat()
    }
  }
}
