package org.teslatoolkit

import io.ktor.client.HttpClient
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.endpoint.TeslaApiEndpoints
import org.teslatoolkit.http.KtorHttpService
import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken

interface TeslaClient {
  suspend fun listVehicles(): List<Vehicle>
  suspend fun getVehicle(id: Long): Vehicle

  suspend fun getOauthToken(request: OauthRequest): AccessToken

  companion object {
    fun create(auth: AuthenticationMethod): TeslaClient =
      TeslaHttpClient(
        http = KtorHttpService(
          client = HttpClient(),
          endpoints = TeslaApiEndpoints.Standard
        ),
        auth = auth
      )
  }
}
