package org.teslatoolkit.auth

import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken

/**
 * Authentication Method that uses an email and password to
 * acquire an access token that will auto-renew if expired.
 */
class AccountAuthentication(
  val email: String,
  val password: String
) : AuthenticationMethod() {
  var token: AccessToken? = null

  override suspend fun getAccessToken(client: TeslaHttpClient): AccessToken {
    if (token == null) {
      token = acquirePasswordToken(client)
    }

    if (token!!.isExpired()) {
      try {
        token = acquireRefreshToken(client, token!!)
      } catch (e: Exception) {
        acquirePasswordToken(client)
      }
    }

    return token!!
  }

  private suspend fun acquirePasswordToken(client: TeslaHttpClient): AccessToken =
    client.getOauthToken(OauthRequest(
      grantType = "password",
      clientId = client.http.endpoints.clientId,
      clientSecret = client.http.endpoints.clientSecret,
      email = email,
      password = password
    ))
}
