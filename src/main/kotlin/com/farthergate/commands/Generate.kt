package com.farthergate.commands

import com.farthergate.gen.BuildConfig
import com.farthergate.goldberg.Config
import com.github.ajalt.mordant.rendering.TextStyles.*
import com.farthergate.goldberg.load
import com.github.ajalt.clikt.core.CliktCommand

class Generate : CliktCommand() {
    override fun run() {
        val config = Config.load()
        println("project\t" + bold(config.versionedName))
        val build = BuildConfig.fromConfig(config)
        build.write()
    }
}