package com.farthergate.commands

import com.farthergate.goldberg.Config
import com.farthergate.goldberg.load
import com.farthergate.util.Format
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.arguments.default
import com.github.ajalt.clikt.parameters.arguments.optional
import kotlin.system.exitProcess

class Run : CliktCommand() {
    val task: String? by argument().optional()

    override fun run() {
        val config = Config.load()

        println("project\t" + Format.bold(config.versionedName))

        val steps = if(task.isNullOrBlank()) config.steps else config.taskSteps[task]

        if(steps == null) {
            println("${Format.red("error")}\tno such script: ${Format.bold(task ?: "")}")
            exitProcess(1)
        }

        task?.let { println("task\t${Format.bold(it)}") }

        for (step in steps) {
            println("step\t" + Format.bold(step.name))
            for (command in step.commands) {
                println("${Format.bold("$")} ${command.joinToString(" ")}")

                val result = ProcessBuilder(command)
                    .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                    .redirectError(ProcessBuilder.Redirect.INHERIT)
                    .redirectInput(ProcessBuilder.Redirect.INHERIT)
                    .start()
                    .waitFor()

                if (result != 0) {
                    println("${Format.red("error")}\tprocess exited with code ${Format.bold(result.toString())}")
                    exitProcess(result)
                }
            }
        }
    }
}