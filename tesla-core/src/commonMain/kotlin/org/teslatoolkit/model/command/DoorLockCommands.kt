package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
class DoorLockCommand : CommandRequest<DoorLockCommand> {
  override val commandName: String
    get() = "door_lock"

  override fun serializer(): KSerializer<DoorLockCommand> =
    DoorLockCommand.serializer()
}

@Serializable
class DoorUnlockCommand : CommandRequest<DoorUnlockCommand> {
  override val commandName: String
    get() = "door_unlock"

  override fun serializer(): KSerializer<DoorUnlockCommand> =
    DoorUnlockCommand.serializer()
}
