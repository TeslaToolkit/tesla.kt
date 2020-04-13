package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SetSentryModeCommand(
  @SerialName("on")
  val on: Boolean
) : CommandRequest<SetSentryModeCommand> {
  override val commandName: String
    get() = "set_sentry_mode"

  override fun serializer(): KSerializer<SetSentryModeCommand> =
    SetSentryModeCommand.serializer()
}
