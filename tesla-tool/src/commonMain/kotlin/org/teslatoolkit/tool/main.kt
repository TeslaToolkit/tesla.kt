package org.teslatoolkit.tool

import com.github.ajalt.clikt.core.subcommands
import org.teslatoolkit.tool.api.ApiCommand
import org.teslatoolkit.tool.api.ApiGetCommand
import org.teslatoolkit.tool.vehicle.VehicleCommand
import org.teslatoolkit.tool.vehicle.VehicleListCommand
import org.teslatoolkit.tool.vehicle.VehicleShowCommand
import org.teslatoolkit.tool.vehicle.show.ShowRawVehicleCommand
import org.teslatoolkit.tool.vehicle.show.ShowVehicleChargeStateCommand
import org.teslatoolkit.tool.vehicle.show.ShowVehicleClimateStateCommand
import org.teslatoolkit.tool.vehicle.show.ShowVehicleConfigCommand
import org.teslatoolkit.tool.vehicle.show.ShowVehicleDriveStateCommand
import org.teslatoolkit.tool.vehicle.show.ShowVehicleGuiSettingsCommand
import org.teslatoolkit.tool.vehicle.show.ShowVehicleStateCommand

fun main(args: Array<String>) {
  TeslaTool().subcommands(
    VehicleCommand().subcommands(
      VehicleListCommand(),
      VehicleShowCommand().subcommands(
        ShowRawVehicleCommand(),
        ShowVehicleConfigCommand(),
        ShowVehicleStateCommand(),
        ShowVehicleChargeStateCommand(),
        ShowVehicleGuiSettingsCommand(),
        ShowVehicleDriveStateCommand(),
        ShowVehicleClimateStateCommand()
      )
    ),
    ApiCommand().subcommands(
      ApiGetCommand()
    )
  ).main(args)
}
