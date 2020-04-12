package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VehicleState(
  @SerialName("api_version")
  val apiVersion: Int? = null,

  @SerialName("autopark_state_v2")
  val autoparkState: String? = null,

  @SerialName("autopark_style")
  val autoparkStyle: String? = null,

  @SerialName("car_version")
  val carVersion: String? = null,

  @SerialName("center_display_state")
  val centerDisplayActiveState: Int? = null,

  @SerialName("df")
  val driverSideFrontDoorState: Int? = null,

  @SerialName("pf")
  val passengerSideFrontDoorState: Int? = null,

  @SerialName("dr")
  val driverSideRearDoorState: Int? = null,

  @SerialName("pr")
  val passengerSideRearDoorState: Int? = null,

  @SerialName("ft")
  val frontTrunkState: Int? = null,

  @SerialName("rt")
  val rearTrunkState: Int? = null,

  @SerialName("homelink_device_count")
  val homeLinkDeviceCount: Int? = null,

  @SerialName("smart_summon_available")
  val isSmartSummonAvailable: Boolean? = null,

  @SerialName("summon_standby_mode_enabled")
  val isSummonStandbyModeEnabled: Boolean? = null,

  @SerialName("homelink_nearby")
  val isHomeLinkNearby: Boolean? = null,

  @SerialName("is_user_present")
  val isUserPresent: Boolean? = null,

  @SerialName("last_autopark_error")
  val lastAutoparkError: String? = null,

  @SerialName("locked")
  val isLocked: Boolean? = null,

  @SerialName("odometer")
  val odometer: Double? = null,

  @SerialName("notifications_supported")
  val isNotificationsSupported: Boolean? = null,

  @SerialName("calendar_supported")
  val isCalendarSupported: Boolean? = null,

  @SerialName("parsed_calendar_supported")
  val isParsedCalendarSupported: Boolean? = null,

  @SerialName("remote_start")
  val isRemoteStart: Boolean? = null,

  @SerialName("remote_start_enabled")
  val isRemoteStartEnabled: Boolean? = null,

  @SerialName("remote_start_supported")
  val isRemoteStartSupported: Boolean? = null,

  @SerialName("timestamp")
  val timestampUnixMillis: Long? = null,

  @SerialName("vehicle_name")
  val vehicleName: String? = null,

  @SerialName("valet_mode")
  val isValetMode: Boolean? = null,

  @SerialName("valet_pin_needed")
  val isValetPinNeeded: Boolean? = null,

  @SerialName("sentry_mode")
  val isSentryMode: Boolean? = null,

  @SerialName("sentry_mode_available")
  val isSentryModeAvailable: Boolean? = null,

  @SerialName("speed_limit_mode")
  val speedLimitMode: SpeedLimitMode? = null,

  @SerialName("media_state")
  val mediaState: MediaState? = null,

  @SerialName("software_update")
  val softwareUpdate: SoftwareUpdate? = null
) {
  val isDriverSideFrontDoorOpen: Boolean =
    driverSideFrontDoorState != 0

  val isPassengerSideFrontDoorOpen: Boolean =
    passengerSideFrontDoorState != 0

  val isDriverSideRearDoorOpen: Boolean =
    driverSideRearDoorState != 0

  val isPassengerSideRearDoorOpen: Boolean =
    passengerSideRearDoorState != 0

  val isFrontTrunkOpen: Boolean =
    frontTrunkState != 0

  val isRearTrunkOpen: Boolean =
    rearTrunkState != 0

  val isCenterDisplayOn: Boolean =
    centerDisplayActiveState != 0
}
