package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleConfig(
  @SerialName("can_accept_navigation_requests")
  val canAcceptNavigationRequests: Boolean? = null,

  @SerialName("can_actuate_trunks")
  val canActuateTrunks: Boolean? = null,

  @SerialName("car_special_type")
  val carSpecialType: String? = null,

  @SerialName("car_type")
  val carType: String? = null,

  @SerialName("charge_port_type")
  val chargePortType: String? = null,

  @SerialName("ece_restrictions")
  val eceRestrictions: Boolean? = null,

  @SerialName("eu_vehicle")
  val euVehicle: Boolean? = null,

  @SerialName("exterior_color")
  val exteriorColor: String? = null,

  @SerialName("has_air_suspension")
  val hasAirSuspension: Boolean? = null,

  @SerialName("has_ludicrous_mode")
  val hasLudicrousMode: Boolean? = null,

  @SerialName("motorized_charge_port")
  val motorizedChargePort: Boolean? = null,

  @SerialName("plg")
  val plg: Boolean? = null,

  @SerialName("rear_seat_heaters")
  val rearSeatHeaters: Int? = null,

  @SerialName("rear_seat_type")
  val rearSeatType: Int? = null,

  @SerialName("rhd")
  val rhd: Boolean? = null,

  @SerialName("roof_color")
  val roofColor: String? = null,

  @SerialName("seat_type")
  val seatType: Int? = null,

  @SerialName("spoiler_type")
  val spoilerType: String? = null,

  @SerialName("sun_roof_installed")
  val sunRoofInstalled: Int? = null,

  @SerialName("third_row_seats")
  val thirdRowSeats: String? = null,

  @SerialName("timestamp")
  val timestamp: Long? = null,

  @SerialName("trim_badging")
  val trimBadging: String? = null,

  @SerialName("use_range_badging")
  val useRangeBadging: Boolean? = null,

  @SerialName("wheel_type")
  val wheelType: String? = null
)
