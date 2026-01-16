package com.farthergate.util

import org.pkl.codegen.kotlin.CliKotlinCodeGenerator
import org.pkl.codegen.kotlin.CliKotlinCodeGeneratorOptions
import org.pkl.commons.cli.CliBaseOptions
import java.net.URI
import kotlin.io.path.Path

internal fun main() {
    CliKotlinCodeGenerator(
        CliKotlinCodeGeneratorOptions(
            CliBaseOptions(listOf(URI("./pkl/Config.pkl"))),
            Path("./src/main/"),
            implementSerializable = true
        )
    ).run()
}