package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SpeedLimitSetLimitCommand(
  @SerialName("limit_mph")
  val limit: Int
) : CommandRequest<SpeedLimitSetLimitCommand> {
  override val commandName: String
    get() = "speed_limit_set_limit"

  override fun serializer(): KSerializer<SpeedLimitSetLimitCommand> =
    SpeedLimitSetLimitCommand.serializer()
}

@Serializable
class SpeedLimitActivateCommand(
  @SerialName("pin")
  val pin: String
) : CommandRequest<SpeedLimitActivateCommand> {
  override val commandName: String
    get() = "speed_limit_activate"

  override fun serializer(): KSerializer<SpeedLimitActivateCommand> =
    SpeedLimitActivateCommand.serializer()
}

@Serializable
class SpeedLimitDeactivateCommand(
  @SerialName("pin")
  val pin: String
) : CommandRequest<SpeedLimitDeactivateCommand> {
  override val commandName: String
    get() = "speed_limit_deactivate"

  override fun serializer(): KSerializer<SpeedLimitDeactivateCommand> =
    SpeedLimitDeactivateCommand.serializer()
}

@Serializable
class SpeedLimitClearPinCommand(
  @SerialName("pin")
  val pin: String
) : CommandRequest<SpeedLimitClearPinCommand> {
  override val commandName: String
    get() = "speed_limit_clear_pin"

  override fun serializer(): KSerializer<SpeedLimitClearPinCommand> =
    SpeedLimitClearPinCommand.serializer()
}
