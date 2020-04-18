package org.teslatoolkit.java

import kotlin.time.ExperimentalTime
import kotlin.time.milliseconds
import kotlinx.coroutines.runBlocking
import org.teslatoolkit.helper.VehicleWakeHelper
import org.teslatoolkit.model.Vehicle

class VehicleWakeHelperSync constructor(val client: TeslaClientSync) {
  @ExperimentalTime
  @JvmOverloads
  fun wake(
    id: Long,
    attempts: Int = 20,
    waitInMilliseconds: Long = 2_000
  ): Vehicle = runBlocking {
    VehicleWakeHelper(client.client).wake(
      id = id,
      attempts = attempts,
      wait = waitInMilliseconds.milliseconds
    )
  }
}
