package org.teslatoolkit.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class Vehicle(
  @SerialName("id")
  val globalId: Long,

  @SerialName("user_id")
  val userId: Long,

  @SerialName("vehicle_id")
  val vehicleId: Long,

  @SerialName("vin")
  val vin: String,

  @SerialName("display_name")
  val displayName: String,

  @SerialName("option_codes")
  val rawOptionCodes: String,

  @SerialName("color")
  val color: String?,

  @SerialName("state")
  val state: String,

  @SerialName("in_service")
  val isInService: Boolean,

  @SerialName("id_s")
  val idString: String,

  @SerialName("calendar_enabled")
  val isCalendarEnabled: Boolean,

  @SerialName("api_version")
  val apiVersion: Int,

  @SerialName("backseat_token")
  val backseatToken: String? = null,

  @SerialName("backseat_token_updated_at")
  val backseatTokenUpdatedAt: String? = null,

  @SerialName("tokens")
  val tokens: List<String>
)
