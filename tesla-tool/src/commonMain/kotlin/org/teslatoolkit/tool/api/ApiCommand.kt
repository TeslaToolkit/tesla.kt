package org.teslatoolkit.tool.api

import com.github.ajalt.clikt.core.CliktCommand

class ApiCommand : CliktCommand(
  name = "api",
  help = "Direct API Access",
  invokeWithoutSubcommand = false
) {
  override fun run() {}
}
