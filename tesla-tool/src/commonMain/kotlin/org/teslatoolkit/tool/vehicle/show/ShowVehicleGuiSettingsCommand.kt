package org.teslatoolkit.tool.vehicle.show

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.tool.OutputFormat
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform
import org.teslatoolkit.tool.vehicle.VehicleSpecificCommand
import org.teslatoolkit.tool.vehicle.printHumanFormat

class ShowVehicleGuiSettingsCommand : VehicleSpecificCommand(
  name = "gui-settings",
  help = "Show Vehicle GUI Settings"
) {
  private val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    requireVehicleWake()

    val settings = getTargetVehicleGuiSettings()

    when (toolContext.format) {
      OutputFormat.Json -> println(toolContext.prettyPrintJson.stringify(
        GuiSettings.serializer(),
        settings
      ))

      OutputFormat.JsonCompact -> println(toolContext.json.stringify(
        GuiSettings.serializer(),
        settings
      ))

      OutputFormat.Human -> settings.printHumanFormat()
    }
  }
}
