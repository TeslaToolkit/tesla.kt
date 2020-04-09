package org.teslatoolkit.auth

import com.soywiz.klock.DateTime
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.endpoint.TeslaApiEndpoints
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken

class AccountAuthentication(
  val email: String,
  val password: String,
  val endpoints: TeslaApiEndpoints
) : AuthenticationMethod {
  var token: AccessToken? = null

  override suspend fun getAccessToken(client: TeslaClient): AccessToken {
    if (token == null || token!!.isExpired())
      token = client.getOauthToken(OauthRequest(
        grantType = "password",
        clientId = endpoints.clientId,
        clientSecret = endpoints.clientSecret,
        email = email,
        password = password
      ))
    return token!!
  }

  private fun AccessToken.isExpired(): Boolean =
    (DateTime.nowUnixLong() - 30_000) > expiresAt.unixMillisLong
}
