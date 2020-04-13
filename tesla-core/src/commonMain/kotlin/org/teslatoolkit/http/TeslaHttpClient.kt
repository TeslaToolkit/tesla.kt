package org.teslatoolkit.http

import io.ktor.client.HttpClient
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.model.ChargeState
import org.teslatoolkit.model.ClimateState
import org.teslatoolkit.model.DriveState
import org.teslatoolkit.model.GuiSettings
import org.teslatoolkit.model.Vehicle
import org.teslatoolkit.model.VehicleConfig
import org.teslatoolkit.model.VehicleState
import org.teslatoolkit.model.command.CommandRequest
import org.teslatoolkit.model.command.CommandResponse
import org.teslatoolkit.model.oauth.OauthRequest
import org.teslatoolkit.token.AccessToken
import org.teslatoolkit.token.JsonAccessToken

class TeslaHttpClient(val http: TeslaHttpService, val auth: AuthenticationMethod) : TeslaClient {
  override suspend fun listVehicles(): List<Vehicle> =
    json.parse(
      JsonResponseWrapper.serializer(ListSerializer(Vehicle.serializer())),
      http.sendGetRequest(
        path = "/api/1/vehicles",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicle(id: Long): Vehicle =
    json.parse(
      JsonResponseWrapper.serializer(
        Vehicle.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicleState(id: Long): VehicleState =
    json.parse(
      JsonResponseWrapper.serializer(
        VehicleState.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id/data_request/vehicle_state",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicleChargeState(id: Long): ChargeState =
    json.parse(
      JsonResponseWrapper.serializer(
        ChargeState.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id/data_request/charge_state",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicleClimateState(id: Long): ClimateState =
    json.parse(
      JsonResponseWrapper.serializer(
        ClimateState.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id/data_request/climate_state",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicleGuiSettings(id: Long): GuiSettings =
    json.parse(
      JsonResponseWrapper.serializer(
        GuiSettings.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id/data_request/gui_settings",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicleDriveState(id: Long): DriveState =
    json.parse(
      JsonResponseWrapper.serializer(
        DriveState.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id/data_request/drive_state",
        token = auth.getAccessToken(this)
      )
    ).response

  override suspend fun getVehicleConfig(id: Long): VehicleConfig =
    json.parse(
      JsonResponseWrapper.serializer(
        VehicleConfig.serializer()
      ),
      http.sendGetRequest(
        path = "/api/1/vehicles/$id/data_request/vehicle_config",
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

  override suspend fun <T : CommandRequest<T>> sendVehicleCommand(id: Long, command: T): CommandResponse =
    json.parse(
      JsonResponseWrapper.serializer(
        CommandResponse.serializer()
      ),
      http.sendPostRequest(
        path = "/api/1/vehicles/$id/command/${command.commandName}",
        token = auth.getAccessToken(this),
        content = json.stringify(command.serializer(), command)
      )
    ).response

  override suspend fun vehicleWakeUp(id: Long): Vehicle =
    json.parse(
      JsonResponseWrapper.serializer(
        Vehicle.serializer()
      ),
      http.sendPostRequest(
        path = "/api/1/vehicles/$id/wake_up",
        token = auth.getAccessToken(this),
        content = ""
      )
    ).response

  override fun close() {
    http.close()
  }

  companion object {
    @Suppress("EXPERIMENTAL_API_USAGE")
    private val json = Json(JsonConfiguration(
      ignoreUnknownKeys = true
    ))

    /**
     * Creates a [TeslaHttpClient] with the provided [AuthenticationMethod].
     *
     * @param auth The authentication method to use.
     * @return The instance of [TeslaHttpClient].
     */
    fun create(auth: AuthenticationMethod): TeslaHttpClient =
      TeslaHttpClient(
        http = KtorHttpService(
          client = HttpClient(),
          endpoints = ApiEndpoints.Standard
        ),
        auth = auth
      )
  }
}
