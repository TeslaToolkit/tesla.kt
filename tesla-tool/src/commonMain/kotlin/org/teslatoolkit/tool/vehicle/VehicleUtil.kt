package org.teslatoolkit.tool.vehicle

import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.ClimateState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleConfig
import org.teslatoolkit.model.VehicleState

private fun Boolean.open() = if (this) "Open" else "Closed"
private fun Boolean.locked() = if (this) "Locked" else "Unlocked"
private fun Boolean.enabled() = if (this) "Enabled" else "Disabled"
private fun Boolean.supported() = if (this) "Supported" else "Unsupported"
private fun Boolean.on() = if (this) "On" else "Off"

fun Vehicle.printHumanFormat() {
  println("Vehicle $globalId:")
  println("  Display Name: $displayName")
  println("  Current State: $state")
  println("  In Service: $isInService")
}

fun VehicleState.printHumanFormat() {
  println("Vehicle State:")
  println("  Firmware Version: $carVersion")
  println("  Lock State: ${isLocked?.locked()}")
  println("  Remote Start State: ${isRemoteStart?.enabled()}")
  println("  Remote Start Support: ${isRemoteStartSupported?.supported()}")
  println("  Doors:")
  println("    Driver Side Front Door: ${isDriverSideFrontDoorOpen.open()}")
  println("    Driver Side Rear Door: ${isDriverSideRearDoorOpen.open()}")
  println("    Passenger Side Front Door: ${isPassengerSideFrontDoorOpen.open()}")
  println("    Passenger Side Rear Door: ${isPassengerSideRearDoorOpen.open()}")
  println("  Front Trunk: ${isFrontTrunkOpen.open()}")
  println("  Rear Trunk: ${isRearTrunkOpen.open()}")
}

fun ChargeState.printHumanFormat() {
  println("Charge State:")
  println("  Battery Heater: ${isBatteryHeaterOn?.on()}")
  println("  Battery Level: $batteryLevel")
  println("  Usable Battery Level: $usableBatteryLevel")
  println("  Battery Range: $batteryRange")
  println("  Estimated Battery Range: $estimatedBatteryRange")
  println("  Ideal Battery Range: $idealBatteryRange")
  println("  Charge Miles Added Ideal: $chargeMilesAddedIdeal")
  println("  Charge Miles Added Rated: $chargeMilesAddedRated")
  println("  Charge Port Door: ${isChargePortDoorOpen?.open()}")
}

fun GuiSettings.printHumanFormat() {
  println("GUI Settings:")
  println("  24-Hour Time: ${isTwentyFourHourTime?.enabled()}")
  println("  Charge Rate Units: $chargeRateUnits")
  println("  Distance Units: $distanceUnits")
  println("  Range Display Type: $rangeDisplayType")
  println("  Temperature Units: $temperatureUnits")
}

fun DriveState.printHumanFormat() {
  println("Drive State:")
  println("  Latitude: $latitude")
  println("  Longitude: $longitude")
  println("  Heading: $heading")
  println("  Power: $power")
  println("  Speed: $speed")
  println("  Shift State: $shiftState")
}

fun ClimateState.printHumanFormat() {
  println("Climate State:")
  println("  Inside Temperature: $insideTemperature")
}

fun VehicleConfig.printHumanFormat() {
  println("Vehicle Configuration:")
  println("  Car Type: $carType")
  println("  Wheel Type: $wheelType")
  println("  Has Air Suspension: $hasAirSuspension")
}
