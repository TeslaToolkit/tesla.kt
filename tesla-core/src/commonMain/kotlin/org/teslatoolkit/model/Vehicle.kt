package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vehicle(
  @SerialName("id")
  val globalId: Long? = null,

  @SerialName("user_id")
  val userId: Long? = null,

  @SerialName("vehicle_id")
  val vehicleId: Long? = null,

  @SerialName("vin")
  val vin: String? = null,

  @SerialName("display_name")
  val displayName: String? = null,

  @SerialName("option_codes")
  val rawOptionCodes: String? = null,

  @SerialName("color")
  val color: String? = null,

  @SerialName("state")
  val state: String? = null,

  @SerialName("in_service")
  val isInService: Boolean? = null,

  @SerialName("id_s")
  val idString: String? = null,

  @SerialName("calendar_enabled")
  val isCalendarEnabled: Boolean? = null,

  @SerialName("api_version")
  val apiVersion: Int? = null,

  @SerialName("backseat_token")
  val backseatToken: String? = null,

  @SerialName("backseat_token_updated_at")
  val backseatTokenUpdatedAt: String? = null,

  @SerialName("tokens")
  val tokens: List<String>? = null
)
