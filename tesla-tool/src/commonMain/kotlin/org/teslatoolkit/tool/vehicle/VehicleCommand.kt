package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.CliktCommand

class VehicleCommand : CliktCommand(
  name = "vehicle",
  help = "Vehicle Commands",
  invokeWithoutSubcommand = false
) {
  override fun run() {}
}
