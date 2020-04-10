package org.teslatoolkit.tool.platform

import kotlinx.coroutines.CoroutineScope

expect object Platform {
  fun completeCoroutineExecution(block: suspend CoroutineScope.() -> Unit)
}
