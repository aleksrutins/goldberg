package com.farthergate.commands

import com.farthergate.gen.BuildConfig
import com.farthergate.goldberg.Config
import com.farthergate.goldberg.load
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.arguments.optional
import kotlin.system.exitProcess
import com.github.ajalt.mordant.rendering.TextColors.*
import com.github.ajalt.mordant.rendering.TextStyles.*

class Run : CliktCommand() {
    val task: String? by argument().optional()

    override fun run() {
        val config = Config.load()

        println("project\t" + bold(config.versionedName))

        BuildConfig.fromConfig(config).writeResources()

        val steps = if(task.isNullOrBlank()) config.steps else config.taskSteps[task]

        if(steps == null) {
            println("${red("error")}\tno such script: ${bold(task ?: "")}")
            exitProcess(1)
        }

        task?.let { println("task\t${bold(it)}") }

        for (step in steps) {
            println("step\t" + bold(step.name))
            for (command in step.commands) {
                println("${bold("$")} ${command.joinToString(" ")}")

                val result = ProcessBuilder(command)
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .redirectError(ProcessBuilder.Redirect.INHERIT)
                    .redirectInput(ProcessBuilder.Redirect.INHERIT)
                    .start()
                    .waitFor()

                if (result != 0) {
                    println("${red("error")}\tprocess exited with code ${bold(result.toString())}")
                    exitProcess(result)
                }
            }
        }
    }
}