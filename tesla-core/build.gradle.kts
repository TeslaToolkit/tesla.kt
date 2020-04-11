import java.nio.charset.StandardCharsets
import java.util.Base64
import org.jetbrains.dokka.gradle.DokkaTask

plugins {
  `maven-publish`
  signing

  kotlin("multiplatform")
  kotlin("plugin.serialization")

  id("org.jetbrains.dokka")
}

val isVersionSnapshot = version.toString().endsWith("SNAPSHOT")

val ktorVersion = "1.3.2"
val kotlinSerializationVersion = "0.20.0"
val kotlinCoroutinesVersion = "1.3.5"
val klockVersion = "1.7.3"

fun ktor(name: String): String = "io.ktor:ktor-$name:$ktorVersion"
fun kotlinx(name: String, version: String): String = "org.jetbrains.kotlinx:kotlinx-$name:$version"
fun klock(name: String): String = "com.soywiz.korlibs.klock:$name:$klockVersion"

val dokka by tasks.getting(DokkaTask::class) {
  outputDirectory = "$rootDir/docs/api"
  outputFormat = "gfm"
  multiplatform {}
}

kotlin {
  sourceSets {
    all {
      languageSettings.useExperimentalAnnotation("kotlin.RequiresOptIn")
    }

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

val emptyJavadocJar by tasks.registering(Jar::class) {
  archiveClassifier.set("javadoc")
}

fun maybeBase64Decode(input: String?): String? =
  if (input != null && input.startsWith("base64_")) {
    String(
      Base64.getDecoder().decode(input.substring(7)),
      StandardCharsets.UTF_8
    )
  } else {
    input
  }

val publishSigningKey: String? =
  project.findProperty("tesla.kt.sign.key")?.toString()
  ?: System.getenv("TESLA_KT_SIGN_KEY")

val publishSigningPassphrase: String? = maybeBase64Decode(
  project.findProperty("tesla.kt.sign.passphrase")?.toString()
  ?: System.getenv("TESLA_KT_SIGN_PASSPHRASE")
)

val publishSonatypeUsername: String? =
  project.findProperty("tesla.kt.sonatype.username")?.toString()
  ?: System.getenv("TESLA_KT_SONATYPE_USERNAME")

val publishSonatypePassword: String? = maybeBase64Decode(
  project.findProperty("tesla.kt.sonatype.password")?.toString()
  ?: System.getenv("TESLA_KT_SONATYPE_PASSWORD")
)

publishing {
  publications.withType<MavenPublication>().all {
    pom {
      description.set("Tesla API Client Library")
      name.set("Tesla Toolkit Core")
      url.set("https://github.com/TeslaToolkit/tesla.kt")
      scm {
        url.set("ttps://github.com/TeslaToolkit/tesla.kt")
        connection.set("scm:git:git://github.com/TeslaToolkit/tesla.kt.git")
        developerConnection.set("scm:git:ssh://git@github.com/TeslaToolkit/tesla.kt.git")
        tag.set("master")
      }

      licenses {
        license {
          name.set("The MIT License")
          url.set("https://opensource.org/licenses/MIT")
          distribution.set("repo")
        }
      }

      developers {
        developer {
          id.set("kendfinger")
          name.set("Kenneth Endfinger")
          url.set("https://github.com/kendfinger")
          email.set("kaendfinger@gmail.com")
          timezone.set("America/New_York")
        }
      }
    }
  }

  publications.withType<MavenPublication>().all {
    artifact(emptyJavadocJar.get())
  }

  repositories {
    val releaseUrl = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
    val snapshotUrl = uri("https://oss.sonatype.org/content/repositories/snapshots/")

    maven {
      url = if (isVersionSnapshot) snapshotUrl else releaseUrl
      credentials {
        username = publishSonatypeUsername ?: ""
        password = publishSonatypePassword ?: ""
      }
    }
  }
}

signing {
  isRequired = !isVersionSnapshot ||
    project.hasProperty("tesla.kt.sign.enforce") ||
    System.getenv("TESLA_KT_SIGN_ENFORCE") == "true"

  if (publishSigningKey != null && publishSigningPassphrase != null && !isVersionSnapshot) {
    val realSigningKey = Base64.getDecoder().decode(publishSigningKey)
    useInMemoryPgpKeys(String(realSigningKey, StandardCharsets.UTF_8), publishSigningPassphrase)
    sign(publishing.publications)
  }
}
