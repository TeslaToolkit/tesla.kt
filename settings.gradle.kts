rootProject.name = "tesla"

include(
  ":tesla-core",
  ":tesla-tool",
  ":examples:simple-java",
  ":examples:simple-kotlin"
)

enableFeaturePreview("GRADLE_METADATA")
