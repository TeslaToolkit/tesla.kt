package org.teslatoolkit.http

import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleState
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken
import org.teslatoolkit.token.JsonAccessToken

class TeslaHttpClient(val http: TeslaHttpService, val auth: AuthenticationMethod) : TeslaClient {
  override suspend fun listVehicles(): List<Vehicle> =
    json.parse(
      JsonResponseWrapper.serializer(ListSerializer(Vehicle.serializer())),
      http.sendGetRequest(
        "/api/1/vehicles",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicle(id: Long): Vehicle =
    json.parse(
      JsonResponseWrapper.serializer(
        Vehicle.serializer()
      ),
      http.sendGetRequest(
        "/api/1/vehicles/$id",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun vehicleWakeUp(id: Long): Vehicle =
    json.parse(
      JsonResponseWrapper.serializer(
        Vehicle.serializer()
      ),
      http.sendPostRequest(
        "/api/1/vehicles/$id/wake_up",
        token = auth.getAccessToken(this),
        content = ""
      )
    ).response

  override suspend fun getVehicleState(id: Long): VehicleState =
    json.parse(
      JsonResponseWrapper.serializer(
        VehicleState.serializer()
      ),
      http.sendGetRequest(
        "/api/1/vehicles/$id/data_request/vehicle_state",
        token = auth.getAccessToken(this)
      )
    ).response

  suspend fun getOauthToken(request: OauthRequest): AccessToken =
    json.parse(
      JsonAccessToken.serializer(),
      http.sendOauthRequest(
        json.stringify(
          OauthRequest.serializer(),
          request
        )
      )
    )

  companion object {
    @Suppress("EXPERIMENTAL_API_USAGE")
    private val json = Json(JsonConfiguration(
      ignoreUnknownKeys = true
    ))
  }
}
