package org.teslatoolkit.tool.vehicle.command

import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.convert
import com.github.ajalt.clikt.parameters.types.choice
import org.teslatoolkit.model.command.SetSentryModeCommand

class ExecuteSetSentryModeCommand : ExecuteVehicleCommand<SetSentryModeCommand>(
  name = "set-sentry-mode",
  help = "Set Sentry Mode"
) {
  private val on by argument("on-mode", help = "On or Off").choice(
    "on",
    "off"
  ).convert { choice ->
    when (choice) {
      "on" -> true
      "off" -> false
      else -> false
    }
  }

  override fun createCommandRequest(): SetSentryModeCommand =
    SetSentryModeCommand(on = on)
}
