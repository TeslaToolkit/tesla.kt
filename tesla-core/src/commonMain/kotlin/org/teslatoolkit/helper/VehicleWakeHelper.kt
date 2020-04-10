package org.teslatoolkit.helper

import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.seconds
import kotlinx.coroutines.delay
import org.teslatoolkit.TeslaClient
import org.teslatoolkit.model.Vehicle

class VehicleWakeHelper(val client: TeslaClient) {
  @ExperimentalTime
  suspend fun wake(id: Long, attempts: Int = 5, wait: Duration = 5.seconds): Vehicle {
    for (attempt in 1..attempts) {
      val result = client.vehicleWakeUp(id)

      if (result.state != "asleep") {
        return result
      }

      if (attempt == attempts) {
        return result
      }

      delay(wait.toLongMilliseconds())
    }
    throw RuntimeException("Should not happen.")
  }
}
