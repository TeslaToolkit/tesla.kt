package org.teslatoolkit.example;

import org.teslatoolkit.auth.AccountAuthentication;
import org.teslatoolkit.auth.AuthenticationMethod;
import org.teslatoolkit.java.TeslaClientSync;
import org.teslatoolkit.model.Vehicle;

public class ExampleMain {
  public static void main(String[] args) {
    AuthenticationMethod auth = new AccountAuthentication(args[0], args[1]);
    try (TeslaClientSync client = TeslaClientSync.create(auth)) {
      for (Vehicle vehicle : client.listVehicles()) {
        System.out.println("Vehicle " + vehicle.getVehicleId());
        System.out.println("  State: " + vehicle.getState());
      }
    }
  }
}
