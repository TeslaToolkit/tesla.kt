package org.teslatoolkit.http

import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.token.AccessToken

class KtorHttpService(val client: HttpClient, override val endpoints: ApiEndpoints) : TeslaHttpService {
  override suspend fun sendGetRequest(path: String, token: AccessToken): String =
    client.get("${endpoints.ownersApiUrl}$path") {
      configureHttpClient(token)
    }

  override suspend fun sendPostRequest(path: String, content: String, token: AccessToken): String =
    client.post("${endpoints.ownersApiUrl}$path") {
      configureHttpClient(token)
      header("Content-Type", "application/json")
      body = content
    }

  override suspend fun sendOauthRequest(content: String): String =
    client.post("${endpoints.ownersApiUrl}/oauth/token") {
      configureHttpClient(null)
      header("Content-Type", "application/json")
      body = content
    }

  override fun close() {
    client.close()
  }

  private fun HttpRequestBuilder.configureHttpClient(token: AccessToken?) {
    header("User-Agent", "Tesla.kt")

    if (token != null) {
      header("Authorization", "Bearer ${token.accessToken}")
    }
  }
}
