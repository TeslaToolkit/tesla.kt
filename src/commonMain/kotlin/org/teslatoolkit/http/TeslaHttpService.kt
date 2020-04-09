package org.teslatoolkit.http

import org.teslatoolkit.token.AccessToken

interface TeslaHttpService {
  suspend fun sendGetRequest(path: String, token: AccessToken? = null): String
  suspend fun sendPostRequest(path: String, content: String, token: AccessToken? = null): String
}
