package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class VehicleState(
  @SerialName("api_version")
  val apiVersion: Int,

  @SerialName("autopark_state_v2")
  val autoparkState: String,

  @SerialName("autopark_style")
  val autoparkStyle: String,

  @SerialName("car_version")
  val carVersion: String,

  @SerialName("center_display_state")
  val centerDisplayActiveState: Int,

  @SerialName("df")
  val driverSideFrontDoorState: Int,
  @SerialName("pf")
  val passengerSideFrontDoorState: Int,
  @SerialName("dr")
  val driverSideRearDoorState: Int,
  @SerialName("pr")
  val passengerSideRearDoorState: Int,

  @SerialName("ft")
  val frontTrunkState: Int,

  @SerialName("rt")
  val rearTrunkState: Int,

  @SerialName("homelink_device_count")
  val homeLinkDeviceCount: Int,

  @SerialName("smart_summon_available")
  val isSmartSummonAvailable: Boolean,

  @SerialName("summon_standby_mode_enabled")
  val isSummonStandbyModeEnabled: Boolean,

  @SerialName("homelink_nearby")
  val isHomeLinkNearby: Boolean,

  @SerialName("is_user_present")
  val isUserPresent: Boolean,

  @SerialName("last_autopark_error")
  val lastAutoparkError: String,

  @SerialName("locked")
  val isLocked: Boolean,

  @SerialName("odometer")
  val odometer: Double,

  @SerialName("notifications_supported")
  val isNotificationsSupported: Boolean,

  @SerialName("calendar_supported")
  val isCalendarSupported: Boolean,

  @SerialName("parsed_calendar_supported")
  val isParsedCalendarSupported: Boolean,

  @SerialName("remote_start")
  val isRemoteStart: Boolean,

  @SerialName("remote_start_enabled")
  val isRemoteStartEnabled: Boolean,

  @SerialName("remote_start_supported")
  val isRemoteStartSupported: Boolean,

  @SerialName("timestamp")
  val timestampUnixMillis: Long,

  @SerialName("vehicle_name")
  val vehicleName: String,

  @SerialName("valet_mode")
  val isValetMode: Boolean,

  @SerialName("valet_pin_needed")
  val isValetPinNeeded: Boolean? = null,

  @SerialName("sentry_mode")
  val isSentryMode: Boolean,

  @SerialName("sentry_mode_available")
  val isSentryModeAvailable: Boolean,

  @SerialName("speed_limit_mode")
  val speedLimitMode: SpeedLimitMode,

  @SerialName("media_state")
  val mediaState: MediaState,

  @SerialName("software_update")
  val softwareUpdate: SoftwareUpdate
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
