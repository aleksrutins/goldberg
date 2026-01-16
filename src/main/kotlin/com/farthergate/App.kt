package com.farthergate

import com.farthergate.commands.Run
import com.farthergate.commands.Generate
import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands

class App : CliktCommand("goldberg") {
    override fun run() = Unit
}

fun main(args: Array<String>) = App()
    .subcommands(Run(), Generate())
    .main(args)