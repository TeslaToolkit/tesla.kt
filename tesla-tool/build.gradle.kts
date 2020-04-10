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
    compilations["main"].defaultSourceSet {
      dependencies {
        implementation(kotlin("stdlib-jdk8"))
        implementation(clikt("clikt"))
      }
    }
  }
}
