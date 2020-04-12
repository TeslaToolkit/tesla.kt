import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.targets.js.npm.NpmResolverPlugin

plugins {
  `maven-publish`
  signing

  kotlin("multiplatform")
  kotlin("plugin.serialization")

  id("org.jetbrains.dokka")
}

plugins.apply(NpmResolverPlugin::class.java)

val ktorVersion = "1.3.2"
val kotlinSerializationVersion = "0.20.0"
val kotlinCoroutinesVersion = "1.3.5"
val klockVersion = "1.7.3"

fun ktor(name: String): String = "io.ktor:ktor-$name:$ktorVersion"
fun kotlinx(name: String, version: String): String = "org.jetbrains.kotlinx:kotlinx-$name:$version"
fun klock(name: String): String = "com.soywiz.korlibs.klock:$name:$klockVersion"

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
