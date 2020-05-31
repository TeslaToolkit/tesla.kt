plugins {
  java

  id("com.github.johnrengelman.shadow")
}

dependencies {
  implementation(project(":tesla-tool"))
}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "org.teslatoolkit.tool.MainKt"
  }
}
