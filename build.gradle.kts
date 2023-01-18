import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.markdownToHTML
import org.jetbrains.intellij.tasks.RunPluginVerifierTask.FailureLevel.COMPATIBILITY_PROBLEMS

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    // Java support
    java
    // Scala support
    scala
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij") version "1.12.0"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "2.0.0"
    id("org.jetbrains.grammarkit") version "2022.3"
    // Gradle Qodana Plugin
    id("org.jetbrains.qodana") version "0.1.13"
    // Gradle Kover Plugin
    id("org.jetbrains.kotlinx.kover") version "0.6.1"
}

val pluginName = properties("pluginName")
val pluginGroup = properties("pluginGroup")
val pluginVersion = properties("pluginVersion")
val scalaVersion = properties("scalaVersion")
val javaVersion = properties("javaVersion")

group = pluginGroup
version = pluginVersion

// Configure project's dependencies
repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.scala-lang:scala-library:$scalaVersion")
    testCompileOnly("org.scala-lang:scala-library:$scalaVersion")
}

// Configure Gradle IntelliJ Plugin - read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName.set(pluginName)
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    groups.set(emptyList())
    repositoryUrl.set(properties("pluginRepositoryUrl"))
}

// Configure Gradle Qodana Plugin - read more: https://github.com/JetBrains/gradle-qodana-plugin
qodana {
    cachePath.set(file(".qodana").canonicalPath)
    reportPath.set(file("build/reports/inspections").canonicalPath)
    saveReport.set(true)
    showReport.set(System.getenv("QODANA_SHOW_REPORT")?.toBoolean() ?: false)
}

// Configure Gradle Kover Plugin - read more: https://github.com/Kotlin/kotlinx-kover#configuration
kover.xmlReport {
    onCheck.set(true)
}

sourceSets {
    main {
        scala {
            setSrcDirs(listOf("src/main/scala"))
        }
        java {
            setSrcDirs(listOf("src/main/gen"))
        }
        resources {
            setSrcDirs(listOf("src/main/resources"))
        }
    }
}

tasks {
    compileJava {
        dependsOn("generateLexer", "generateParser")
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    generateLexer {
        source.set("src/main/scala/org/broadinstitute/winstanley/Wdl.flex")
        targetDir.set("src/main/gen/org/broadinstitute/winstanley/lexer/")
        targetClass.set("_WdlLexer")
        purgeOldFiles.set(true)
    }

    generateParser {
        source.set("src/main/scala/org/broadinstitute/winstanley/wdl.bnf")
        targetRoot.set("src/main/gen")
        pathToParser.set("org/broadinstitute/winstanley/WdlParser.java")
        pathToPsiRoot.set("org/broadinstitute/winstanley/psi")
        purgeOldFiles.set(true)
    }

    buildPlugin {
        archiveBaseName.set("ignore")
    }

    runPluginVerifier {
        failureLevel.set(listOf(COMPATIBILITY_PROBLEMS))
    }

    patchPluginXml {
        version.set(pluginVersion)
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(
            file("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end))
            }.joinToString("\n").let { markdownToHTML(it) }
        )

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            with(changelog) {
                renderItem(
                    getOrNull(pluginVersion)
                        ?: runCatching { getLatest() }.getOrElse { getUnreleased() },
                    Changelog.OutputType.HTML,
                )
            }
        })
    }

    // Configure UI tests plugin
    // Read more: https://github.com/JetBrains/intellij-ui-test-robot
    runIdeForUiTests {
        systemProperty("robot-server.port", "8082")
        systemProperty("ide.mac.message.dialogs.as.sheets", "false")
        systemProperty("jb.privacy.policy.text", "<!--999.999-->")
        systemProperty("jb.consents.confirmation.enabled", "false")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
        // The pluginVersion is based on the SemVer (https://semver.org) and supports pre-release labels, like 2.1.7-alpha.3
        // Specify pre-release label to publish the plugin in a custom Release Channel automatically. Read more:
        // https://plugins.jetbrains.com/docs/intellij/deployment.html#specifying-a-release-channel
        channels.set(listOf(pluginVersion.split('-').getOrElse(1) { "default" }.split('.').first()))
    }
}
