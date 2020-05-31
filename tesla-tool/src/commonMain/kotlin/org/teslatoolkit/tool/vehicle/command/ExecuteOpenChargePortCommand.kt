package org.teslatoolkit.tool.vehicle.command

import org.teslatoolkit.model.command.OpenChargePortCommand

class ExecuteOpenChargePortCommand : ExecuteVehicleCommand<OpenChargePortCommand>(
  name = "open-charge-port",
  help = "Opens the Charge Port Door"
) {
  override fun createCommandRequest(): OpenChargePortCommand =
    OpenChargePortCommand()
}
