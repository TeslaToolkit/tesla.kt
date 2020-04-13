package org.teslatoolkit.tool.vehicle.command

import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.types.choice
import org.teslatoolkit.model.command.ActuateTrunkCommand

class ExecuteActuateTrunkCommand : ExecuteVehicleCommand<ActuateTrunkCommand>(
  name = "actuate-trunk",
  help = "Actuate the Trunk"
) {
  private val trunk by option(
    "--which",
    help = "Trunk to Actuate"
  ).choice(
    "rear",
    "front"
  ).default("rear")

  override fun createCommandRequest(): ActuateTrunkCommand =
    ActuateTrunkCommand(trunk)
}
