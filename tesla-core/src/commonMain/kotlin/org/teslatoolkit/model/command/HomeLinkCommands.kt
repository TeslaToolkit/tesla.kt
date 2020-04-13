package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
class TriggerHomeLinkCommand : CommandRequest<TriggerHomeLinkCommand> {
  override val commandName: String
    get() = "remote_start_drive"

  override fun serializer(): KSerializer<TriggerHomeLinkCommand> =
    TriggerHomeLinkCommand.serializer()
}
