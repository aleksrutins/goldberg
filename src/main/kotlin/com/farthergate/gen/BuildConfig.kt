package com.farthergate.gen

import com.farthergate.goldberg.Config
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.encodeToString
import net.mamoe.yamlkt.Yaml
import java.io.File
import kotlin.io.path.Path
import kotlin.io.path.createParentDirectories
import kotlin.io.path.writeText

@Serializable
data class BuildConfig(
    val image: String,
    val packages: List<String>,
    val oauth: String,
    val environment: Map<String, String>,

    val tasks: List<Map<String, String>>,

    @Transient val resources: Map<String, String> = mapOf()
) {
    companion object {
        fun fromConfig(config: Config):
                BuildConfig {
            return BuildConfig(
                config.ci.image,
                (config.ci.packages + config.steps.flatMap { step -> step.packages }).distinct().sorted(),
                config.ci.oauth.joinToString(" "),
                config.ci.environment,

                config.steps.map { step ->
                    mapOf(step.name to listOf(
                        listOf("cd", config.source.repo),
                        *step.commands.toTypedArray()
                    ).joinToString("\n") { cmd ->
                        cmd.joinToString(" ") { "\"" + it.replace("\"", "\\\"") + "\"" }
                    }) },

                config.steps.map { step -> step.resources }.reduce { acc, map -> acc + map }
            )
        }
    }

    fun write() {
        val out = Yaml.encodeToString(BuildConfig.serializer(), this)

        File(".build.yml").writeText(out)

        for (res in resources) {
            val p = Path("./build-aux", res.key)
            p.createParentDirectories()
            p.writeText(res.value)
        }
    }
}
