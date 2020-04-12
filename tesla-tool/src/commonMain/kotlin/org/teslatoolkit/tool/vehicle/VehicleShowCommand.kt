package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.CliktCommand

class VehicleShowCommand : CliktCommand(
  name = "show",
  help = "Show Vehicle Data",
  invokeWithoutSubcommand = false
) {
  override fun run() {}
}
