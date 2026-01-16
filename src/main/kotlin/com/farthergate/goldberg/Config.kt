package com.farthergate.goldberg

import java.io.Serializable
import kotlin.Long
import kotlin.String
import kotlin.collections.List
import kotlin.collections.Map

data class Config(
  val versionedName: String,
  val name: String,
  val version: String,
  val use: List<Preset>,
  val source: Source,
  val steps: List<Step>,
  val tasks: Map<String, List<Preset>>,
  val taskSteps: Map<String, List<Step>>,
  val ci: CI
) : Serializable {
  data class Source(
    val repo: String
  ) : Serializable {
    companion object {
      private const val serialVersionUID: Long = 0L
    }
  }

  data class CI(
    val image: String,
    val packages: List<String>,
    val oauth: List<String>,
    val environment: Map<String, String>
  ) : Serializable {
    companion object {
      private const val serialVersionUID: Long = 0L
    }
  }

  data class Preset(
    val name: String,
    val defaultSteps: List<Step>
  ) : Serializable {
    companion object {
      private const val serialVersionUID: Long = 0L
    }
  }

  data class Step(
    val name: String,
    val commands: List<List<String>>,
    val packages: List<String>,
    val resources: Map<String, String>
  ) : Serializable {
    companion object {
      private const val serialVersionUID: Long = 0L
    }
  }

  companion object {
    private const val serialVersionUID: Long = 0L
  }
}
