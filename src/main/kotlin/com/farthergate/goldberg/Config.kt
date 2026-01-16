package com.farthergate.goldberg

import kotlin.String
import kotlin.collections.List

data class Config(
  val versionedName: String,
  val name: String,
  val version: String,
  val use: List<Preset>,
  val source: Source,
  val steps: List<Step>
) {
  data class Source(
    val repo: String
  )

  data class Preset(
    val name: String,
    val defaultSteps: List<Step>
  )

  data class Step(
    val name: String,
    val commands: List<List<String>>
  )
}
