package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform

class ShowVehicleCommand : VehicleSpecificCommand(
  name = "show",
  help = "Show Vehicle"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    val vehicle = possibleVehicleWake()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        Vehicle.serializer(),
        vehicle
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        Vehicle.serializer(),
        vehicle
      ))

      OutputFormat.Human -> vehicle.printHumanFormat()
    }
  }
}
