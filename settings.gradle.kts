rootProject.name = "tesla"

include(
  ":tesla-core",
  ":tesla-tool",
  ":examples:simple-java"
)

enableFeaturePreview("GRADLE_METADATA")
