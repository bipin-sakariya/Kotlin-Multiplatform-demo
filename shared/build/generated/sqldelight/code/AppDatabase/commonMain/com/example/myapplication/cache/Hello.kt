package com.example.myapplication.cache

import kotlin.Long
import kotlin.String

public data class Hello(
  public val id: Long,
  public val date: String,
  public val name: String,
  public val project: String
) {
  public override fun toString(): String = """
  |Hello [
  |  id: $id
  |  date: $date
  |  name: $name
  |  project: $project
  |]
  """.trimMargin()
}
