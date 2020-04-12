package org.teslatoolkit.tool

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.findOrSetObject
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import com.github.ajalt.clikt.parameters.options.required
import com.github.ajalt.clikt.parameters.types.enum
import com.soywiz.klock.DateTime
import com.soywiz.klock.DateTimeSpan
import io.ktor.client.HttpClient
import org.teslatoolkit.auth.AccountAuthentication
import org.teslatoolkit.auth.AuthenticationMethod
import org.teslatoolkit.auth.TokenAuthentication
import org.teslatoolkit.endpoint.ApiEndpoints
import org.teslatoolkit.http.KtorHttpService
import org.teslatoolkit.http.TeslaHttpClient
import org.teslatoolkit.token.JsonAccessToken

class TeslaTool : CliktCommand(
  help = "Tesla Control",
  name = "teslactl") {
  private val toolContext by findOrSetObject { ToolContext() }

  private val teslaEmail by option(
    "--email",
    envvar = "TESLA_EMAIL",
    help = "Tesla Account Email"
  ).required()

  private val teslaPassword by option(
    "--password",
    envvar = "TESLA_PASSWORD",
    help = "Tesla Account Password"
  ).required()

  private val teslaToken by option(
    "--token",
    envvar = "TESLA_TOKEN",
    help = "Tesla Account Token"
  )

  private val format by option(
    "--format",
    help = "Output Format"
  ).enum<OutputFormat> { format ->
    format.simple
  }.default(OutputFormat.Human)

  override fun run() {
    toolContext.auth = createClientAuthentication()

    toolContext.http = KtorHttpService(
      client = HttpClient(),
      endpoints = ApiEndpoints.Standard
    )

    toolContext.client = TeslaHttpClient(
      http = toolContext.http!!,
      auth = toolContext.auth!!
    )
    toolContext.format = format
  }

  private fun createClientAuthentication(): AuthenticationMethod =
    if (teslaToken != null) {
      TokenAuthentication(
        token = JsonAccessToken(
          accessToken = teslaToken!!,
          refreshToken = "",
          createdAtRaw = DateTime.nowUnixLong(),
          expiresInRaw = DateTime.now().plus(
            DateTimeSpan(years = 30)
          ).unixMillisLong
        )
      )
    } else {
      AccountAuthentication(
        email = teslaEmail,
        password = teslaPassword
      )
    }
}
