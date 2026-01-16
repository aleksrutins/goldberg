package com.farthergate.goldberg

import org.pkl.config.java.ConfigEvaluator
import org.pkl.config.kotlin.forKotlin
import org.pkl.config.kotlin.to
import org.pkl.core.ModuleSource
import kotlin.use

fun Config.Companion.load() =
    ConfigEvaluator.preconfigured().forKotlin()
        .use { evaluator -> evaluator.evaluate(ModuleSource.file("goldberg.pkl")) }
        .to<Config>()