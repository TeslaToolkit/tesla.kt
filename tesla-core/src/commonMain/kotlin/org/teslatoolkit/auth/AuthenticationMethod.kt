package org.teslatoolkit.auth

import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken

abstract class AuthenticationMethod {
  abstract suspend fun getAccessToken(client: TeslaHttpClient): AccessToken

  protected suspend fun acquireRefreshToken(client: TeslaHttpClient, token: AccessToken): AccessToken =
    client.getOauthToken(OauthRequest(
      grantType = "refresh_token",
      clientId = client.http.endpoints.clientId,
      clientSecret = client.http.endpoints.clientSecret,
      refreshToken = token.refreshToken
    ))
}
