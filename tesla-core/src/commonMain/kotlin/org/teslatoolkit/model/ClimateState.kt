package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ClimateState(
  @SerialName("battery_heater")
  val batteryHeater: Boolean? = null,

  @SerialName("battery_heater_no_power")
  val batteryHeaterNoPower: Boolean? = null,

  @SerialName("climate_keeper_mode")
  val climateKeeperMode: String? = null,

  @SerialName("defrost_mode")
  val defrostModeInt: Int? = null,

  @SerialName("driver_temp_setting")
  val driverTemperatureSetting: Double? = null,

  @SerialName("fan_status")
  val fanStatusInt: Int? = null,

  @SerialName("inside_temp")
  val insideTemperature: Double? = null,

  @SerialName("is_auto_conditioning_on")
  val isAutoConditioningOn: Boolean? = null,

  @SerialName("is_climate_on")
  val isClimateOn: Boolean? = null,

  @SerialName("is_front_defroster_on")
  val isFrontDefrosterOn: Boolean? = null,

  @SerialName("is_preconditioning")
  val isPreconditioning: Boolean? = null,

  @SerialName("is_rear_defroster_on")
  val isRearDefrosterOn: Boolean? = null,

  @SerialName("left_temp_direction")
  val leftTemperatureDirection: Int? = null,

  @SerialName("max_avail_temp")
  val maxAvailTemperature: Double? = null,

  @SerialName("min_avail_temp")
  val minAvailTemperature: Double? = null,

  @SerialName("outside_temp")
  val outsideTemperature: Double? = null,

  @SerialName("passenger_temp_setting")
  val passengerTempSetting: Double? = null,

  @SerialName("remote_heater_control_enabled")
  val remoteHeaterControlEnabled: Boolean? = null,

  @SerialName("right_temp_direction")
  val rightTemperatureDirection: Int? = null,

  @SerialName("seat_heater_left")
  val seatHeaterLeftState: Int? = null,

  @SerialName("seat_heater_rear_center")
  val seatHeaterRearCenterState: Int? = null,

  @SerialName("seat_heater_rear_left")
  val seatHeaterRearLeftState: Int? = null,

  @SerialName("seat_heater_rear_left_back")
  val seatHeaterRearLeftBackState: Int? = null,

  @SerialName("seat_heater_rear_right")
  val seatHeaterRearRightState: Int? = null,

  @SerialName("seat_heater_rear_right_back")
  val seatHeaterRearRightBackState: Int? = null,

  @SerialName("seat_heater_right")
  val seatHeaterRight: Int? = null,

  @SerialName("side_mirror_heaters")
  val sideMirrorHeaters: Boolean? = null,

  @SerialName("steering_wheel_heater")
  val steeringWheelHeater: Boolean? = null,

  @SerialName("timestamp")
  val timestamp: Long? = null,

  @SerialName("wiper_blade_heater")
  val wiperBladeHeater: Boolean? = null
)
