package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SoftwareUpdate(
  @SerialName("download_perc")
  val downloadPercentage: Double? = null,

  @SerialName("expected_duration_sec")
  val expectedDurationSeconds: Int? = null,

  @SerialName("install_perc")
  val installPercentage: Double? = null,

  @SerialName("scheduled_time_ms")
  val scheduledTimeMilliseconds: Long? = null,

  @SerialName("status")
  val status: String? = null,

  @SerialName("version")
  val version: String? = null
)
