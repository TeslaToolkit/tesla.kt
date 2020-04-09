package org.teslatoolkit.auth

import org.teslatoolkit.TeslaClient
import org.teslatoolkit.token.AccessToken

class TokenAuthentication(val token: AccessToken) : AuthenticationMethod {
  override suspend fun getAccessToken(client: TeslaClient): AccessToken = token
}
