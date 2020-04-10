package org.teslatoolkit.endpoint

interface ApiEndpoints {
  val ownersApiUrl: String
  val summonConnectUrl: String
  val clientId: String
  val clientSecret: String

  companion object {
    val Standard = StandardApiEndpoints
  }
}
