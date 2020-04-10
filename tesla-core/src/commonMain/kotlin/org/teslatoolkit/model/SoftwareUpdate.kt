package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class SoftwareUpdate(
  @SerialName("download_perc")
  val downloadPercentage: Double,

  @SerialName("expected_duration_sec")
  val expectedDurationSeconds: Int,

  @SerialName("install_perc")
  val installPercentage: Double,

  @SerialName("scheduled_time_ms")
  val scheduledTimeMilliseconds: Long = 0,

  @SerialName("status")
  val status: String = "unknown",

  @SerialName("version")
  val version: String
)
