package org.teslatoolkit.tool.vehicle

import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState

fun Vehicle.printHumanFormat() {
  println("Vehicle $globalId:")
  println("  Display Name: $displayName")
  println("  Current State: $state")
  println("  In Service: $isInService")
}

fun VehicleState.printHumanFormat() {
  fun Boolean.open() = if (this) "Open" else "Closed"
  fun Boolean.locked() = if (this) "Locked" else "Unlocked"
  fun Boolean.enabled() = if (this) "Enabled" else "Disabled"
  fun Boolean.supported() = if (this) "Supported" else "Unsupported"

  println("Vehicle State:")
  println("  Firmware Version: $carVersion")
  println("  Lock State: ${isLocked.locked()}")
  println("  Remote Start State: ${isRemoteStart.enabled()}")
  println("  Remote Start Support: ${isRemoteStartSupported.supported()}")
  println("  Doors:")
  println("    Driver Side Front Door: ${isDriverSideFrontDoorOpen.open()}")
  println("    Driver Side Rear Door: ${isDriverSideRearDoorOpen.open()}")
  println("    Passenger Side Front Door: ${isPassengerSideFrontDoorOpen.open()}")
  println("    Passenger Side Rear Door: ${isPassengerSideRearDoorOpen.open()}")
  println("  Front Trunk: ${isFrontTrunkOpen.open()}")
  println("  Rear Trunk: ${isRearTrunkOpen.open()}")
}
