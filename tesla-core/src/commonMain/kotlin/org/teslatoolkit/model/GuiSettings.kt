package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class GuiSettings(
  @SerialName("gui_24_hour_time")
  val isTwentyFourHourTime: Boolean,

  @SerialName("gui_charge_rate_units")
  val chargeRateUnits: String,

  @SerialName("gui_distance_units")
  val distanceUnits: String,

  @SerialName("gui_range_display")
  val rangeDisplayType: String,

  @SerialName("gui_temperature_units")
  val temperatureUnits: String,

  @SerialName("timestamp")
  val timestampUnixMillis: Long
)
