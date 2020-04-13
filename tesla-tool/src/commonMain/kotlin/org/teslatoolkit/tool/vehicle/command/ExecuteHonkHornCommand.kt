package org.teslatoolkit.tool.vehicle.command

import org.teslatoolkit.model.command.HonkHornCommand

class ExecuteHonkHornCommand : ExecuteVehicleCommand<HonkHornCommand>(
  name = "honk-horn",
  help = "Honk Horn"
) {
  override fun createCommandRequest(): HonkHornCommand =
    HonkHornCommand()
}
