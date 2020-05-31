plugins {
  application
  java

  id("com.github.johnrengelman.shadow")
}

application {
  mainClassName = "org.teslatoolkit.tool.MainKt"
}

dependencies {
  implementation(project(":tesla-tool"))
}

tasks.jar {
  manifest {
    attributes["Main-Class"] = "org.teslatoolkit.tool.MainKt"
  }
}
