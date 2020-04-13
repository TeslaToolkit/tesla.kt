package org.teslatoolkit.model.command

import kotlinx.serialization.Serializable

@Serializable
data class CommandResponse(
  val reason: String? = null,
  val result: Boolean = false
)
