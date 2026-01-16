package com.farthergate

import com.farthergate.goldberg.Config
import com.farthergate.util.Format
import org.pkl.config.java.ConfigEvaluator
import org.pkl.config.kotlin.forKotlin
import org.pkl.config.kotlin.to
import org.pkl.core.ModuleSource
import kotlin.system.exitProcess

fun main() {
    val config = ConfigEvaluator.preconfigured().forKotlin()
        .use { evaluator -> evaluator.evaluate(ModuleSource.file("goldberg.pkl")) }
        .to<Config>()

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