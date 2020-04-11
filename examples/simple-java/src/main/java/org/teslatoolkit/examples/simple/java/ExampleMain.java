package org.teslatoolkit.examples.simple.java;

import io.ktor.client.HttpClient;
import io.ktor.client.HttpClientConfig;
import io.ktor.client.engine.cio.CIO;
import kotlin.Unit;
import org.teslatoolkit.TeslaClient;
import org.teslatoolkit.auth.AccountAuthentication;
import org.teslatoolkit.endpoint.StandardApiEndpoints;
import org.teslatoolkit.http.KtorHttpService;
import org.teslatoolkit.http.TeslaHttpClient;
import org.teslatoolkit.java.TeslaClientSync;

public class ExampleMain {
  public static void main(String[] args) {
    TeslaClient kotlinClient =
        new TeslaHttpClient(
            new KtorHttpService(
                new HttpClient(CIO.INSTANCE.create((f) -> Unit.INSTANCE), new HttpClientConfig<>()),
                StandardApiEndpoints.INSTANCE),
            new AccountAuthentication(args[0], args[1]));

    TeslaClientSync client = new TeslaClientSync(kotlinClient);
    client
        .listVehicles()
        .forEach(
            (it) -> {
              System.out.println("Vehicle " + it.getVehicleId());
            });
  }
}
