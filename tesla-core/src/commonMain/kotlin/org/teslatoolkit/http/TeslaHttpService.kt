package org.teslatoolkit.http

import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.token.AccessToken

interface TeslaHttpService {
  val endpoints: ApiEndpoints

  suspend fun sendGetRequest(path: String, token: AccessToken): String
  suspend fun sendPostRequest(path: String, content: String, token: AccessToken): String
  suspend fun sendOauthRequest(content: String): String
}
