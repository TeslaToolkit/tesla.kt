package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ScheduleSoftwareUpdateCommand(
  @SerialName("offset_sec")
  val waitInSeconds: Int = 0
) : CommandRequest<ScheduleSoftwareUpdateCommand> {
  override val commandName: String
    get() = "schedule_software_update"

  override fun serializer(): KSerializer<ScheduleSoftwareUpdateCommand> =
    ScheduleSoftwareUpdateCommand.serializer()
}
