rootProject.name = "archetypes"

include(
    "archetypes",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = uri("https://packages.confluent.io/maven/"))
        maven(url = uri("https://jitpack.io"))
    }
}
