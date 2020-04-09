import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  kotlin("multiplatform") version "1.3.71"
  kotlin("plugin.serialization") version "1.3.71"

  id("com.diffplug.gradle.spotless") version "3.27.2"
}

group = "org.teslatoolkit"
version = "0.1.0"

val ktorVersion = "1.3.2"
val kotlinSerializationVersion = "0.20.0"
val kotlinCoroutinesVersion = "1.3.5"
val klockVersion = "1.7.3"

fun ktor(name: String): String = "io.ktor:ktor-$name:$ktorVersion"
fun kotlinx(name: String, version: String): String = "org.jetbrains.kotlinx:kotlinx-$name:$version"
fun klock(name: String): String = "com.soywiz.korlibs.klock:$name:$klockVersion"

repositories {
  jcenter()
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

tasks.withType<KotlinCompile> {
  kotlinOptions {
    jvmTarget = "1.8"
    freeCompilerArgs = listOf("-progressive")
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "6.3"
  distributionType = Wrapper.DistributionType.ALL
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(kotlin("stdlib-common"))
        implementation(kotlinx("serialization-runtime-common", kotlinSerializationVersion))
        implementation(ktor("client-serialization"))
        implementation(ktor("client-cio"))
        implementation(klock("klock"))
      }
    }

    commonTest {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
  }

  jvm {
    compilations["main"].defaultSourceSet {
      dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlinx("serialization-runtime", kotlinSerializationVersion))
        implementation(kotlinx("coroutines-core", kotlinCoroutinesVersion))
        implementation(ktor("client-serialization-jvm"))
      }
    }

    compilations["test"].defaultSourceSet {
      dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit5"))
      }
    }
  }
}
