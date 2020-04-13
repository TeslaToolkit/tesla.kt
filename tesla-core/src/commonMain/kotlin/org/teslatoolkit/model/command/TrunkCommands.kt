package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ActuateTrunkCommand(
  @SerialName("which_trunk")
  val trunk: String
) : CommandRequest<ActuateTrunkCommand> {
  override val commandName: String
    get() = "actuate_trunk"

  override fun serializer(): KSerializer<ActuateTrunkCommand> =
    ActuateTrunkCommand.serializer()
}
