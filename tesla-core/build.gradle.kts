import org.gradle.internal.os.OperatingSystem as GradleOperatingSystem
import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmResolverPlugin
import org.jetbrains.kotlin.konan.target.Family as KonanTargetFamily

plugins {
  `maven-publish`
  signing

  kotlin("multiplatform")
  kotlin("plugin.serialization")

  id("org.jetbrains.dokka")
}

plugins.apply(NpmResolverPlugin::class.java)

val ktorVersion: String by project
val kotlinSerializationVersion: String by project
val kotlinCoroutinesVersion: String by project
val klockVersion: String by project

fun ktor(name: String): String = "io.ktor:ktor-$name:$ktorVersion"
fun kotlinx(name: String, version: String): String = "org.jetbrains.kotlinx:kotlinx-$name:$version"
fun klock(name: String): String = "com.soywiz.korlibs.klock:$name:$klockVersion"

fun KotlinNativeTarget.configureNativeTarget() {
  compilations["main"].defaultSourceSet {
    kotlin.srcDir("src/posixMain/kotlin")
    dependencies {
      implementation(klock("klock-${targetName.toLowerCase()}"))
      implementation(kotlinx("serialization-runtime-native", kotlinSerializationVersion))
      implementation(ktor("client-serialization-native"))

      if (arrayOf(
          KonanTargetFamily.IOS,
          KonanTargetFamily.WATCHOS,
          KonanTargetFamily.TVOS).contains(konanTarget.family)) {
        implementation(ktor("client-ios"))
      } else {
        implementation(ktor("client-curl"))
      }
    }
  }
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        api(kotlin("stdlib-common"))
        api(kotlinx("serialization-runtime-common", kotlinSerializationVersion))
        api(ktor("client-serialization"))
        api(klock("klock"))
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
    mavenPublication {
      artifactId = "tesla-core-jvm"
    }

    compilations["main"].defaultSourceSet {
      dependencies {
        api(kotlin("stdlib-jdk8"))
        api(kotlinx("serialization-runtime", kotlinSerializationVersion))
        api(kotlinx("coroutines-core", kotlinCoroutinesVersion))
        api(ktor("client-serialization-jvm"))
        api(ktor("client-cio"))
      }
    }

    compilations["test"].defaultSourceSet {
      dependencies {
        implementation(kotlin("test"))
        implementation(kotlin("test-junit5"))
      }
    }
  }

  js {
    mavenPublication {
      artifactId = "tesla-core-js"
    }

    compilations["main"].defaultSourceSet {
      dependencies {
        api(kotlin("stdlib-js"))
        api(kotlinx("serialization-runtime-js", kotlinSerializationVersion))
        api(kotlinx("coroutines-core-js", kotlinCoroutinesVersion))
        api(ktor("client-serialization-js"))
        api(ktor("client-js"))

        implementation(npm("abort-controller"))
        implementation(npm("node-fetch"))
        implementation(npm("text-encoding"))
      }
    }

    compilations["test"].defaultSourceSet {
      dependencies {
        implementation(kotlin("test-js"))
      }
    }
  }

  if (GradleOperatingSystem.current().isLinux) {
    linuxX64().configureNativeTarget()
  }

  if (GradleOperatingSystem.current().isMacOsX) {
    macosX64().configureNativeTarget()
    iosArm64().configureNativeTarget()
    iosArm32().configureNativeTarget()
    iosX64().configureNativeTarget()
    watchosArm32().configureNativeTarget()
    watchosArm64().configureNativeTarget()
    watchosX86().configureNativeTarget()
    tvosArm64().configureNativeTarget()
    tvosX64().configureNativeTarget()
  }

  if (GradleOperatingSystem.current().isWindows) {
    mingwX64().configureNativeTarget()
    mingwX86().configureNativeTarget()
  }
}

tasks.withType<DokkaTask> {
  multiplatform {
    register("js") {}
    register("jvm") {}
    register("common") {}

    register("global") {
      sourceLink {
        path = "src/main/kotlin"
        url = "https://github.com/TeslaToolkit/tesla.kit/blob/master/${project.name}/src/main/kotlin"
        lineSuffix = "#L"
      }
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
      name.set("Tesla Toolkit Core")
      description.set("Tesla API Client Library")
    }
  }
}
