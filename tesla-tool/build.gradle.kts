import org.gradle.internal.os.OperatingSystem as GradleOperatingSystem
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

val cliktVersion: String by project
fun clikt(name: String): String = "com.github.ajalt:$name:$cliktVersion"

val enableNativeToolTargets: Boolean = project.properties.getOrDefault(
  "enableNativeToolTargets", false
) as Boolean

fun KotlinNativeTarget.configureNativeTarget() {
  binaries {
    executable {
      entryPoint = "org.teslatoolkit.tool.main"
    }
  }

  compilations["main"].defaultSourceSet {
    kotlin.srcDir("src/posixMain/kotlin")
  }
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        implementation(project(":tesla-core"))

        implementation(kotlin("stdlib-common"))
        implementation(clikt("clikt-multiplatform"))
      }
    }
  }

  jvm {
    mavenPublication {
      artifactId = "tesla-tool-jvm"
    }
  }

  if (enableNativeToolTargets) {
    if (GradleOperatingSystem.current().isLinux) {
      linuxX64().configureNativeTarget()
    }

    if (GradleOperatingSystem.current().isMacOsX) {
      macosX64().configureNativeTarget()
    }

    if (GradleOperatingSystem.current().isWindows) {
      mingwX64().configureNativeTarget()
      mingwX86().configureNativeTarget()
    }
  }
}

val emptyJavadocJar by tasks.registering(Jar::class) {
  archiveClassifier.set("javadoc")
}

publishing {
  publications.withType<MavenPublication>().all {
    artifact(emptyJavadocJar.get())

    pom {
      name.set("Tesla Toolkit Tool")
      description.set("Tesla CLI Tool")
    }
  }
}
