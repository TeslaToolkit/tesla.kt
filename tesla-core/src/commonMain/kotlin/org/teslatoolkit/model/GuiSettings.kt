package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuiSettings(
  @SerialName("gui_24_hour_time")
  val isTwentyFourHourTime: Boolean? = null,

  @SerialName("gui_charge_rate_units")
  val chargeRateUnits: String? = null,

  @SerialName("gui_distance_units")
  val distanceUnits: String? = null,

  @SerialName("gui_range_display")
  val rangeDisplayType: String? = null,

  @SerialName("gui_temperature_units")
  val temperatureUnits: String? = null,

  @SerialName("timestamp")
  val timestampUnixMillis: Long? = null
)
