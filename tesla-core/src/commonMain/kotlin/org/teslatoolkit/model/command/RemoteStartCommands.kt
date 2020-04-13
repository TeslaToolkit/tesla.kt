package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class RemoteStartDriveCommand(
  @SerialName("password")
  val password: String
) : CommandRequest<RemoteStartDriveCommand> {
  override val commandName: String
    get() = "remote_start_drive"

  override fun serializer(): KSerializer<RemoteStartDriveCommand> =
    RemoteStartDriveCommand.serializer()
}
