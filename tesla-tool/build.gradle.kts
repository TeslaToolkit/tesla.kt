plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
}

val cliktVersion = "2.6.0"
fun clikt(name: String): String = "com.github.ajalt:$name:$cliktVersion"

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
