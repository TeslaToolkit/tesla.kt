package org.teslatoolkit.token

import com.soywiz.klock.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class JsonAccessToken(
  @SerialName("access_token")
  override val accessToken: String,

  @SerialName("refresh_token")
  override val refreshToken: String,

  @SerialName("created_at")
  private val createdAtRaw: Long,

  @SerialName("expires_in")
  private val expiresInRaw: Long
) : AccessToken {
  override val expiresAt: DateTime
    get() = DateTime.fromUnix(createdAtRaw * 1000)

  override val createdAt: DateTime
    get() = DateTime.fromUnix((createdAtRaw + expiresInRaw) * 1000)
}
