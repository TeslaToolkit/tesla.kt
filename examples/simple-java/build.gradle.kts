plugins {
  java
}

dependencies {
  implementation(project(":tesla-core"))
}

spotless {
  java {
    googleJavaFormat()
  }
}
