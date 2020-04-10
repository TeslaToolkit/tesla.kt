package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import kotlinx.serialization.builtins.ListSerializer
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform

class ListVehicleCommand : CliktCommand(
  name = "list",
  help = "List Vehicles"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    val vehicles = toolContext.client!!.listVehicles()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        ListSerializer(Vehicle.serializer()),
        vehicles
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        ListSerializer(Vehicle.serializer()),
        vehicles
      ))

      OutputFormat.Human -> vehicles.forEach(Vehicle::printHumanFormat)
    }
  }
}
