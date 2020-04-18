package org.teslatoolkit.example;

import org.jetbrains.annotations.NotNull;
import org.teslatoolkit.auth.AccountAuthentication;
import org.teslatoolkit.java.TeslaClientSync;
import org.teslatoolkit.java.VehicleWakeHelperSync;
import org.teslatoolkit.model.Vehicle;
import org.teslatoolkit.model.VehicleData;

public class ExampleMain {
  public static void main(@NotNull String[] args) {
    TeslaClientSync client = TeslaClientSync.create(new AccountAuthentication(args[0], args[1]));

    VehicleWakeHelperSync wakeHelper = new VehicleWakeHelperSync(client);

    for (Vehicle vehicle : client.listVehicles()) {
      wakeHelper.wake(vehicle.getGlobalId());
      handleVehicle(client, vehicle);
    }
  }

  public static void handleVehicle(@NotNull TeslaClientSync client, @NotNull Vehicle vehicle) {
    VehicleData data = client.getVehicleData(vehicle.getGlobalId());
    System.out.println(String.format("Display Name: %s", data.getDisplayName()));
  }
}
