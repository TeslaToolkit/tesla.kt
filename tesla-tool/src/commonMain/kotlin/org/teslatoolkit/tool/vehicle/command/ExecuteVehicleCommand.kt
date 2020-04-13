package org.teslatoolkit.tool.vehicle.command

import com.github.ajalt.clikt.core.requireObject
import org.teslatoolkit.model.command.CommandRequest
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform
import org.teslatoolkit.tool.vehicle.VehicleSpecificCommand

abstract class ExecuteVehicleCommand<T : CommandRequest<T>>(
  val name: String,
  val help: String
) : VehicleSpecificCommand(
  name = name,
  help = help
) {
  val toolContext: ToolContext by requireObject()

  override fun run() = Platform.completeCoroutineExecution {
    val response = toolContext.client!!.sendVehicleCommand(
      id = targetVehicleId,
      command = createCommandRequest()
    )

    if (!response.result) {
      throw RuntimeException("Failed to execute vehicle command.")
    }
  }

  abstract fun createCommandRequest(): T
}
