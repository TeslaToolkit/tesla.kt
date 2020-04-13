package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class WindowControlCommand(
  @SerialName("command")
  val action: String,

  @SerialName("lat")
  val latitude: Double = 0.0,

  @SerialName("lon")
  val longitude: Double = 0.0
) : CommandRequest<WindowControlCommand> {
  override val commandName: String
    get() = "window_control"

  override fun serializer(): KSerializer<WindowControlCommand> =
    WindowControlCommand.serializer()
}
