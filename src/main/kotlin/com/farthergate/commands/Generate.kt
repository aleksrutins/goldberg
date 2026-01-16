package com.farthergate.commands

import com.farthergate.gen.BuildConfig
import com.farthergate.goldberg.Config
import com.farthergate.goldberg.load
import com.github.ajalt.clikt.core.CliktCommand

class Generate : CliktCommand() {
    override fun run() {
        val build = BuildConfig.fromConfig(Config.load())
        build.write()
    }
}