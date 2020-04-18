
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.nio.charset.StandardCharsets
import java.util.Base64

plugins {
  kotlin("multiplatform") version "1.3.72" apply false
  kotlin("plugin.serialization") version "1.3.72" apply false
  id("org.jetbrains.dokka") version "0.10.1" apply false

  id("com.diffplug.gradle.spotless") version "3.27.2"
}

allprojects {
  apply(plugin = "com.diffplug.gradle.spotless")

  spotless {
    format("misc") {
      target("**/*.md", "**/.gitignore")

      trimTrailingWhitespace()
      endWithNewline()
    }
  }
}

subprojects {
  group = "org.teslatoolkit"
  version = "0.2.0-SNAPSHOT"

  val isVersionSnapshot = version.toString().endsWith("SNAPSHOT")

  apply(plugin = "signing")
  apply(plugin = "maven-publish")

  repositories {
    jcenter()
  }

  tasks.withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = "1.8"
      freeCompilerArgs = listOf(
        "-progressive",
        "-Xopt-in=kotlin.RequiresOptIn"
      )
    }
  }

  spotless {
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

  extensions.getByType(PublishingExtension::class.java).apply {
    publications.withType<MavenPublication>().all {
      pom {
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

  extensions.getByType(SigningExtension::class.java).apply {
    isRequired = !isVersionSnapshot ||
      project.hasProperty("tesla.kt.sign.enforce") ||
      System.getenv("TESLA_KT_SIGN_ENFORCE") == "true"

    if (publishSigningKey != null && publishSigningPassphrase != null && !isVersionSnapshot) {
      val realSigningKey = java.util.Base64.getDecoder().decode(publishSigningKey)
      useInMemoryPgpKeys(String(realSigningKey, java.nio.charset.StandardCharsets.UTF_8), publishSigningPassphrase)
      sign(extensions.getByType(PublishingExtension::class.java).publications)
    }
  }
}

tasks.withType<Wrapper> {
  gradleVersion = "6.3"
  distributionType = Wrapper.DistributionType.ALL
}
