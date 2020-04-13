package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SunroofControlCommand(
  @SerialName("state")
  val state: String
) : CommandRequest<SunroofControlCommand> {
  override val commandName: String
    get() = "sun_roof_control"

  override fun serializer(): KSerializer<SunroofControlCommand> =
    SunroofControlCommand.serializer()
}
