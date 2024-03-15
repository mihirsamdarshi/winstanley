import com.jetbrains.plugin.structure.base.utils.contentBuilder.buildDirectory

plugins {
    id("java")
    id("scala")
    id("idea")

    id("org.jetbrains.intellij") version "1.16.1"
    id("org.jetbrains.grammarkit") version "2022.3.2.2"
}

group = "edu.stanford"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1.5")
    type.set("IC") // Target IDE Platform
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.12")
}

grammarKit {
    jflexRelease.set("1.7.0-1")
    grammarKitRelease.set("2021.1.2")
    intellijRelease.set("203.7717.81")
}

sourceSets {
    main {
        scala {
            srcDir("src/main/scala")
            srcDir(layout.buildDirectory.dir("generated/java"))
        }
    }
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }

    withType<ScalaCompile> {
        scalaCompileOptions.additionalParameters = listOf("-target:jvm-17")
    }

    generateLexer {
        sourceFile.set(file("src/main/scala/winstanley/Wdl.flex"))
        targetOutputDir.set(layout.buildDirectory.dir("generated/java/winstanley"))

    }

    generateParser {
        sourceFile.set(file("src/main/scala/winstanley/Wdl.bnf"))
        targetRootOutputDir.set(layout.buildDirectory.dir("generated/java"))
        pathToParser.set("winstanley/parser")
        pathToPsiRoot.set("winstanley/psi")
    }

    patchPluginXml {
        sinceBuild.set("231")
        untilBuild.set("241.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
