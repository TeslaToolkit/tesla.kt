package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MediaState(
  @SerialName("remote_control_enabled")
  val isRemoteControlEnabled: Boolean? = null
)
