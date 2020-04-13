package org.teslatoolkit.tool.vehicle.command

import org.teslatoolkit.model.command.DoorLockCommand

class ExecuteDoorLockCommand : ExecuteVehicleCommand<DoorLockCommand>(
  name = "door-lock",
  help = "Lock the Doors"
) {
  override fun createCommandRequest(): DoorLockCommand =
    DoorLockCommand()
}
