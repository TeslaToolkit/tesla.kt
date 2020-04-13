package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SetValetModeCommand(
  @SerialName("on")
  val on: Boolean,

  @SerialName("password")
  val pin: String = ""
) : CommandRequest<SetValetModeCommand> {
  override val commandName: String
    get() = "set_valet_mode"

  override fun serializer(): KSerializer<SetValetModeCommand> =
    SetValetModeCommand.serializer()
}

@Serializable
class ResetValetPinCommand : CommandRequest<ResetValetPinCommand> {
  override val commandName: String
    get() = "reset_valet_pin"

  override fun serializer(): KSerializer<ResetValetPinCommand> =
    ResetValetPinCommand.serializer()
}
