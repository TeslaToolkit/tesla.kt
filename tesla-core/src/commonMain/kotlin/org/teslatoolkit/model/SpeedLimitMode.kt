package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpeedLimitMode(
  @SerialName("active")
  val isActive: Boolean,

  @SerialName("pin_code_set")
  val isPinCodeSet: Boolean,

  @SerialName("current_limit_mph")
  val currentLimitMph: Double,

  @SerialName("max_limit_mph")
  val maxLimitMph: Double,

  @SerialName("min_limit_mph")
  val minLimitMph: Double
)
