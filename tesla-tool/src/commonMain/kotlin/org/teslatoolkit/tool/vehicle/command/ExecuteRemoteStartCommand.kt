package org.teslatoolkit.tool.vehicle.command

import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import org.teslatoolkit.model.command.RemoteStartDriveCommand

class ExecuteRemoteStartCommand : ExecuteVehicleCommand<RemoteStartDriveCommand>(
  name = "remote-start",
  help = "Enable Key-less Driving Mode"
) {
  private val password by option(
    "--password",
    help = "Account Password",
    envvar = "TESLA_PASSWORD"
  ).required()

  override fun createCommandRequest(): RemoteStartDriveCommand =
    RemoteStartDriveCommand(password)
}
