package org.teslatoolkit.auth

import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken

/**
 * An authentication method for use with [TeslaHttpClient] instances.
 */
abstract class AuthenticationMethod {
  /**
   * Acquires an [AccessToken] that is valid for use by the
   * [TeslaHttpClient] specified as [client].
   *
   * @param client The [TeslaHttpClient] that can be used to acquire a token.
   * @return A valid [AccessToken] for use with the API.
   */
  abstract suspend fun getAccessToken(client: TeslaHttpClient): AccessToken

  protected suspend fun acquireRefreshToken(client: TeslaHttpClient, token: AccessToken): AccessToken =
    client.getOauthToken(OauthRequest(
      grantType = "refresh_token",
      clientId = client.http.endpoints.clientId,
      clientSecret = client.http.endpoints.clientSecret,
      refreshToken = token.refreshToken
    ))
}
