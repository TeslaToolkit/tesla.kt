plugins {
  application
  java
}

application {
  mainClassName = "org.teslatoolkit.tool.MainKt"
}

dependencies {
  implementation(project(":tesla-tool"))
}
