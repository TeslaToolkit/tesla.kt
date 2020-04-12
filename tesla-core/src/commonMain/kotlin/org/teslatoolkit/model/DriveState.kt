package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriveState(
  @SerialName("gps_as_of")
  val gpsAsOfUnixMillis: Long? = null,

  @SerialName("heading")
  val heading: Double? = null,

  @SerialName("latitude")
  val latitude: Double? = null,

  @SerialName("longitude")
  val longitude: Double? = null,

  @SerialName("native_location_supported")
  val nativeLocationSupported: Int? = null,

  @SerialName("native_type")
  val nativeType: String? = null,

  @SerialName("native_latitude")
  val nativeLatitude: Double? = null,

  @SerialName("native_longitude")
  val nativeLongitude: Double? = null,

  @SerialName("power")
  val power: Double? = null,

  @SerialName("shift_state")
  val shiftState: String? = null,

  @SerialName("speed")
  val speed: Double? = null
)
