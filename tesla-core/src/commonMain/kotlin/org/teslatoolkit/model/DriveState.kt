package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DriveState(
  @SerialName("gps_as_of")
  val gpsAsOfUnixMillis: Long?,

  @SerialName("heading")
  val heading: Double?,

  @SerialName("latitude")
  val latitude: Double?,

  @SerialName("longitude")
  val longitude: Double?,

  @SerialName("native_location_supported")
  val nativeLocationSupported: Int?,

  @SerialName("native_type")
  val nativeType: String?,

  @SerialName("native_latitude")
  val nativeLatitude: Double?,

  @SerialName("native_longitude")
  val nativeLongitude: Double?,

  @SerialName("power")
  val power: Double?,

  @SerialName("shift_state")
  val shiftState: String?,

  @SerialName("speed")
  val speed: Double?
)
