package org.teslatoolkit.token

import com.soywiz.klock.DateTime

interface AccessToken {
  val accessToken: String
  val refreshToken: String
  val createdAt: DateTime
  val expiresAt: DateTime
}
