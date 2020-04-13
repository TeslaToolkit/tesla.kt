package org.teslatoolkit.tool.vehicle.command

import org.teslatoolkit.model.command.FlashLightsCommand

class ExecuteFlashLightsCommand : ExecuteVehicleCommand<FlashLightsCommand>(
  name = "flash-lights",
  help = "Flash Lights"
) {
  override fun createCommandRequest(): FlashLightsCommand =
    FlashLightsCommand()
}
