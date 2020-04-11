package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ChargeState(
  @SerialName("battery_heater_on")
  val isBatteryHeaterOn: Boolean,

  @SerialName("battery_level")
  val batteryLevel: Double,

  @SerialName("usable_battery_level")
  val usableBatteryLevel: Double,

  @SerialName("battery_range")
  val batteryRange: Double,

  @SerialName("charge_current_request")
  val chargeCurrentRequest: Double,

  @SerialName("charge_current_request_max")
  val chargeCurrentRequestMax: Double,

  @SerialName("charge_enable_request")
  val chargeEnableRequest: Boolean,

  @SerialName("charge_energy_added")
  val chargeEnergyAdded: Double,

  @SerialName("charge_limit_soc")
  val chargeLimitSoc: Double,

  @SerialName("charge_limit_soc_max")
  val chargeLimitSocMax: Double,

  @SerialName("charge_limit_soc_min")
  val chargeLimitSocMin: Double,

  @SerialName("charge_limit_soc_std")
  val chargeLimitSocStd: Double,

  @SerialName("charge_miles_added_ideal")
  val chargeMilesAddedIdeal: Double,

  @SerialName("charge_miles_added_rated")
  val chargeMilesAddedRated: Double,

  @SerialName("charge_port_door_open")
  val isChargePortDoorOpen: Boolean,

  @SerialName("charge_port_latch")
  val chargePortLatch: String,

  @SerialName("charge_rate")
  val chargeRate: Double,

  @SerialName("charge_to_max_range")
  val chargeToMaxRange: Boolean,

  @SerialName("charger_actual_current")
  val chargerActualCurrent: Double,

  @SerialName("charger_pilot_current")
  val chargerPilotCurrent: Double,

  @SerialName("charging_state")
  val chargingState: String,

  @SerialName("est_battery_range")
  val estimatedBatteryRange: Double,

  @SerialName("ideal_battery_range")
  val idealBatteryRange: Double,

  @SerialName("not_enough_power_to_heat")
  val isNotEnoughPowerToHeat: Boolean,

  @SerialName("trip_charging")
  val isTripCharging: Boolean,

  @SerialName("timestamp")
  val timestampUnixMillis: Long
)
