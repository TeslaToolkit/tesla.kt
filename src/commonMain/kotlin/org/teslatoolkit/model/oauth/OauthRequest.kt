package org.teslatoolkit.model.oauth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OauthRequest(
  @SerialName("grant_type")
  val grantType: String,

  @SerialName("client_id")
  val clientId: String,

  @SerialName("client_secret")
  val clientSecret: String,

  @SerialName("email")
  val email: String? = null,

  @SerialName("password")
  val password: String? = null,

  @SerialName("refresh_token")
  val refreshToken: String? = null
)
