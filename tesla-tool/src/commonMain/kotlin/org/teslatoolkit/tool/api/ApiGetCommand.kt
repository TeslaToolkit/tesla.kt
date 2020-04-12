package org.teslatoolkit.tool.api

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.requireObject
import com.github.ajalt.clikt.parameters.arguments.argument
import org.teslatoolkit.tool.ToolContext
import org.teslatoolkit.tool.platform.Platform

class ApiGetCommand : CliktCommand(
  name = "get",
  help = "Get Resource"
) {
  val toolContext: ToolContext by requireObject()

  private val path: String by argument("path")

  override fun run() = Platform.completeCoroutineExecution {
    var actualPath = path
    if (actualPath.startsWith("/")) {
      actualPath = actualPath.substring(1)
    }

    val response = toolContext.http!!.sendGetRequest(
      path = "/api/1/$actualPath",
      token = toolContext.auth!!.getAccessToken(toolContext.client!!)
    )

    println(response.trim())
  }
}
