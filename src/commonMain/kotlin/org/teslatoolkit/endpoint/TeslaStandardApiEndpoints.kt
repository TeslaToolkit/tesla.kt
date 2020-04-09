package org.teslatoolkit.endpoint

object TeslaStandardApiEndpoints : TeslaApiEndpoints {
  override val ownersApiUrl: String
    get() = "https://owner-api.teslamotors.com"
  override val summonConnectUrl: String
    get() = "wss://streaming.vn.teslamotors.com/connect"
  override val clientId: String
    get() = "81527cff06843c8634fdc09e8ac0abefb46ac849f38fe1e431c2ef2106796384"
  override val clientSecret: String
    get() = "c7257eb71a564034f9419ee651c7d0e5f7aa6bfbd18bafb5c5c033b093bb2fa3"
}
