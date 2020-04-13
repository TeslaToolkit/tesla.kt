package org.teslatoolkit.tool.vehicle.command

import org.teslatoolkit.model.command.DoorUnlockCommand

class ExecuteDoorUnlockCommand : ExecuteVehicleCommand<DoorUnlockCommand>(
  name = "door-unlock",
  help = "Unlock the Doors"
) {
  override fun createCommandRequest(): DoorUnlockCommand =
    DoorUnlockCommand()
}
