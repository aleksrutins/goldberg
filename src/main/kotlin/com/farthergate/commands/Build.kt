package com.farthergate.commands

import com.farthergate.goldberg.Config
import com.farthergate.goldberg.load
import com.farthergate.util.Format
import com.github.ajalt.clikt.core.CliktCommand
import kotlin.system.exitProcess

class Build : CliktCommand() {
    override fun run() {
        val config = Config.load()

        println("project\t" + Format.bold(config.versionedName))

        for (step in config.steps) {
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