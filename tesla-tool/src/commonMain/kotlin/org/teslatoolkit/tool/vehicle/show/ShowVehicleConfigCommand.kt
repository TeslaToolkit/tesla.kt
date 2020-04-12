package org.teslatoolkit.tool.vehicle.show

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.VehicleConfig
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform
import org.teslatoolkit.tool.vehicle.VehicleSpecificCommand
import org.teslatoolkit.tool.vehicle.printHumanFormat

class ShowVehicleConfigCommand : VehicleSpecificCommand(
  name = "config",
  help = "Show Vehicle Configuration"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    requireVehicleWake()

    val config = getTargetVehicleConfig()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        VehicleConfig.serializer(),
        config
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        VehicleConfig.serializer(),
        config
      ))

      OutputFormat.Human -> config.printHumanFormat()
    }
  }
}
