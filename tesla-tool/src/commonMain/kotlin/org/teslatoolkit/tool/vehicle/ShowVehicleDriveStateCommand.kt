package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform

class ShowVehicleDriveStateCommand : VehicleSpecificCommand(
  name = "show-drive-state",
  help = "Show Vehicle Drive State"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    requireVehicleWake()

    val state = getTargetVehicleDriveState()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        DriveState.serializer(),
        state
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        DriveState.serializer(),
        state
      ))

      OutputFormat.Human -> state.printHumanFormat()
    }
  }
}
