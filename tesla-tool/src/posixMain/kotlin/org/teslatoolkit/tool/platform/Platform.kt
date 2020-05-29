package org.teslatoolkit.tool.platform

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

actual object Platform {
  actual fun completeCoroutineExecution(block: suspend CoroutineScope.() -> Unit) {
    runBlocking {
      block()
    }
  }
}
