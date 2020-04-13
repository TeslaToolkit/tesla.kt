package org.teslatoolkit.tool.vehicle

import com.github.ajalt.clikt.core.CliktCommand

class VehicleExecuteCommand : CliktCommand(
  name = "command",
  help = "Execute Vehicle Command",
  invokeWithoutSubcommand = false
) {
  override fun run() {}
}
