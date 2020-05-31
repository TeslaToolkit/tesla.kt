package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class TriggerHomeLinkCommand(
  @SerialName("lat")
  val latitude: Double = 0.0,

  @SerialName("lon")
  val longitude: Double = 0.0
) : CommandRequest<TriggerHomeLinkCommand> {
  override val commandName: String
    get() = "trigger_homelink"

  override fun serializer(): KSerializer<TriggerHomeLinkCommand> =
    TriggerHomeLinkCommand.serializer()
}
