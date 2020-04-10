package org.teslatoolkit.tool

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.teslatoolkit.TeslaClient

class ToolContext {
  var client: TeslaClient? = null
  var format: OutputFormat = OutputFormat.Human

  val prettyPrintJson: Json = Json(JsonConfiguration.Stable.copy(
    prettyPrint = true,
    indent = "  "
  ))

  val json: Json = Json(JsonConfiguration.Stable)
}
