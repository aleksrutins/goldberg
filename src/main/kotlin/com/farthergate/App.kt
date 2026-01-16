package com.farthergate

import com.farthergate.commands.Build
import com.farthergate.commands.Generate
import com.farthergate.goldberg.Config
import com.farthergate.util.Format
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import org.pkl.config.java.ConfigEvaluator
import org.pkl.config.kotlin.forKotlin
import org.pkl.config.kotlin.to
import org.pkl.core.ModuleSource
import kotlin.system.exitProcess

class App : CliktCommand("goldberg") {
    override fun run() = Unit
}

fun main(args: Array<String>) = App()
    .subcommands(Build(), Generate())
    .main(args)