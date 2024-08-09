rootProject.name = "os-app-template"

include(
    "os-app-template",
)

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven(url = uri("https://packages.confluent.io/maven/"))
        maven(url = uri("https://jitpack.io"))
    }
}
