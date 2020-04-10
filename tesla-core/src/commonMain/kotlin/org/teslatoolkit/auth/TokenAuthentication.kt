package org.teslatoolkit.auth

import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.token.AccessToken

class TokenAuthentication(var token: AccessToken) : AuthenticationMethod() {
  override suspend fun getAccessToken(client: TeslaHttpClient): AccessToken {
    if (token.isExpired()) {
      token = acquireRefreshToken(client, token)
    }
    return token
  }
}
