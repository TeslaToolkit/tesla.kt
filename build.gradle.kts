import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("multiplatform") version "1.3.71" apply false
  kotlin("plugin.serialization") version "1.3.71" apply false

  id("com.diffplug.gradle.spotless") version "3.27.2"
}

subprojects {
  group = "org.teslatoolkit"
  version = "0.1.0-SNAPSHOT"

  apply(plugin = "com.diffplug.gradle.spotless")

  repositories {
    jcenter()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf("-progressive")
    }
  }

  spotless {
    format("misc") {
      target("**/*.md", "**/.gitignore")

      trimTrailingWhitespace()
      endWithNewline()
    }

    kotlin {
      target(fileTree("src") {
        include("**/*.kt")
      })

      ktlint().userData(mapOf(
        "indent_size" to "2",
        "continuation_indent_size" to "2"
      ))
      endWithNewline()
    }

    kotlinGradle {
      ktlint().userData(mapOf(
        "indent_size" to "2",
        "continuation_indent_size" to "2"
      ))
      endWithNewline()
    }
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "6.3"
  distributionType = Wrapper.DistributionType.ALL
}
