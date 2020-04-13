package org.teslatoolkit.model.command

import kotlinx.serialization.KSerializer

interface CommandRequest<T> {
  val commandName: String

  fun serializer(): KSerializer<T>
}
