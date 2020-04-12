package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChargeState(
  @SerialName("battery_heater_on")
  val isBatteryHeaterOn: Boolean? = null,

  @SerialName("battery_level")
  val batteryLevel: Double? = null,

  @SerialName("usable_battery_level")
  val usableBatteryLevel: Double? = null,

  @SerialName("battery_range")
  val batteryRange: Double? = null,

  @SerialName("charge_current_request")
  val chargeCurrentRequest: Double? = null,

  @SerialName("charge_current_request_max")
  val chargeCurrentRequestMax: Double? = null,

  @SerialName("charge_enable_request")
  val chargeEnableRequest: Boolean? = null,

  @SerialName("charge_energy_added")
  val chargeEnergyAdded: Double? = null,

  @SerialName("charge_limit_soc")
  val chargeLimitSoc: Double? = null,

  @SerialName("charge_limit_soc_max")
  val chargeLimitSocMax: Double? = null,

  @SerialName("charge_limit_soc_min")
  val chargeLimitSocMin: Double? = null,

  @SerialName("charge_limit_soc_std")
  val chargeLimitSocStd: Double? = null,

  @SerialName("charge_miles_added_ideal")
  val chargeMilesAddedIdeal: Double? = null,

  @SerialName("charge_miles_added_rated")
  val chargeMilesAddedRated: Double? = null,

  @SerialName("charge_port_door_open")
  val isChargePortDoorOpen: Boolean? = null,

  @SerialName("charge_port_latch")
  val chargePortLatch: String? = null,

  @SerialName("charge_rate")
  val chargeRate: Double? = null,

  @SerialName("charge_to_max_range")
  val chargeToMaxRange: Boolean? = null,

  @SerialName("charger_actual_current")
  val chargerActualCurrent: Double? = null,

  @SerialName("charger_pilot_current")
  val chargerPilotCurrent: Double? = null,

  @SerialName("charging_state")
  val chargingState: String? = null,

  @SerialName("est_battery_range")
  val estimatedBatteryRange: Double? = null,

  @SerialName("ideal_battery_range")
  val idealBatteryRange: Double? = null,

  @SerialName("not_enough_power_to_heat")
  val isNotEnoughPowerToHeat: Boolean? = null,

  @SerialName("trip_charging")
  val isTripCharging: Boolean? = null,

  @SerialName("timestamp")
  val timestampUnixMillis: Long? = null
)
