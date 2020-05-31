package org.teslatoolkit.tool.vehicle.command

import org.teslatoolkit.model.command.CloseChargePortCommand

class ExecuteCloseChargePortCommand : ExecuteVehicleCommand<CloseChargePortCommand>(
  name = "close-charge-port",
  help = "Closes the Charge Port Door"
) {
  override fun createCommandRequest(): CloseChargePortCommand =
    CloseChargePortCommand()
}
