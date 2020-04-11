package org.teslatoolkit.examples.simple.java;

import org.teslatoolkit.auth.AccountAuthentication;
import org.teslatoolkit.java.TeslaClientSync;

public class ExampleMain {
  public static void main(String[] args) {
    TeslaClientSync client = TeslaClientSync.create(new AccountAuthentication(args[0], args[1]));

    client
        .listVehicles()
        .forEach(
            (it) -> {
              System.out.println("Vehicle " + it.getVehicleId());
            });
  }
}
