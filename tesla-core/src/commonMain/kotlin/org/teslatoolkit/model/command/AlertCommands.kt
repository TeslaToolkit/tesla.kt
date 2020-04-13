package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
class HonkHornCommand : CommandRequest<HonkHornCommand> {
  override val commandName: String
    get() = "honk_horn"

  override fun serializer(): KSerializer<HonkHornCommand> =
    HonkHornCommand.serializer()
}

@Serializable
class FlashLightsCommand : CommandRequest<FlashLightsCommand> {
  override val commandName: String
    get() = "flash_lights"

  override fun serializer(): KSerializer<FlashLightsCommand> =
    FlashLightsCommand.serializer()
}
