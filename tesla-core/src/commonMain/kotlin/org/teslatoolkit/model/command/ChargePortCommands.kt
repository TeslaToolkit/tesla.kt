package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
class OpenChargePortCommand : CommandRequest<OpenChargePortCommand> {
  override val commandName: String
    get() = "charge_port_door_open"

  override fun serializer(): KSerializer<OpenChargePortCommand> =
    OpenChargePortCommand.serializer()
}

@Serializable
class CloseChargePortCommand : CommandRequest<CloseChargePortCommand> {
  override val commandName: String
    get() = "charge_port_door_close"

  override fun serializer(): KSerializer<CloseChargePortCommand> =
    CloseChargePortCommand.serializer()
}
