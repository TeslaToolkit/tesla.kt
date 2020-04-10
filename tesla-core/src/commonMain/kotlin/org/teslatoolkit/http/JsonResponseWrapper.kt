package org.teslatoolkit.http

import kotlinx.serialization.Serializable

@Serializable
class JsonResponseWrapper<T>(
  val response: T
)
