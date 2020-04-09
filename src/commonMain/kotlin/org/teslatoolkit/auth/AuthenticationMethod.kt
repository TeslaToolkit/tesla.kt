package org.teslatoolkit.auth

import org.teslatoolkit.TeslaClient
import org.teslatoolkit.token.AccessToken

interface AuthenticationMethod {
  suspend fun getAccessToken(client: TeslaClient): AccessToken
}
