plugins {
  `maven-publish`

  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

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
        api(ktor("client-cio"))
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
}

publishing {
  repositories {
    maven {
      name = "github-packages"
      url = uri("https://maven.pkg.github.com/TeslaToolkit/tesla.kt")

      credentials {
        username = (
          project.findProperty("github.packages.username")
          ?: System.getenv("GITHUB_PACKAGES_USERNAME")
          ?: "none"
        ).toString()

        password = (
          project.findProperty("github.packages.token")
          ?: System.getenv("GITHUB_PACKAGES_TOKEN")
          ?: "none"
        ).toString()
      }
    }
  }
}
