package org.teslatoolkit.example

import org.teslatoolkit.auth.AccountAuthentication
import org.teslatoolkit.java.TeslaClientSync

class ExampleMain {
  static void main(String[] args) {
    def client = TeslaClientSync.create(
      new AccountAuthentication(
        args[0],
        args[1]
      )
    )

    client.listVehicles().each { vehicle ->
      println("Vehicle ${vehicle.globalId}:")
      println("  State: ${vehicle.state}")
    }

    client.close()
  }
}
