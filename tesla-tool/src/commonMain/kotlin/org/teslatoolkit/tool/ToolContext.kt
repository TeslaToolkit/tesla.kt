package org.teslatoolkit.tool

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.http.TeslaHttpService

class ToolContext {
  var auth: AuthenticationMethod? = null
  var http: TeslaHttpService? = null
  var client: TeslaHttpClient? = null
  var format: OutputFormat = OutputFormat.Human

  val prettyPrintJson: Json = Json(JsonConfiguration.Stable.copy(
    prettyPrint = true,
    indent = "  "
  ))

  val json: Json = Json(JsonConfiguration.Stable)
}
