import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.spotless) apply true
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.spring.dependency.management) apply false
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven { url = uri("https://packages.confluent.io/maven/") }
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        compilerOptions {
            allWarningsAsErrors = true
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.add("-Xjsr305=strict")
        }
    }
}

ext {
    set("snakeyaml.version", "2.0") // https://avd.aquasec.com/nvd/2022/cve-2022-1471/
    set("jackson-bom.version", libs.versions.jackson.get())
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target(
            fileTree(".") {
                include("**/*.kt")
                exclude(
                    "**/.gradle/**",
                    "**/openapi/**",
                    "**/build/generated/**",
                    "**/buildSrc/build/**",
                )
            },
        )
        // see https://github.com/shyiko/ktlint#standard-rules
        ktlint()
            .setEditorConfigPath("$rootDir/.editorconfig")
    }
    kotlinGradle {
        target(
            fileTree(".") {
                include("**/*.gradle.kts")
                exclude("**/.gradle/**", "**/openapi/**", "**/build/generated/**", "**/buildSrc/build/**")
            },
        )
        trimTrailingWhitespace()
        ktlint()
            .setEditorConfigPath("$rootDir/.editorconfig")
    }
    sql {
        target("**/src/main/resources/db/migration/*.sql")
        dbeaver()
    }
    json {
        target("**/src/test/resources/**/*.json")
        jackson()
    }
    yaml {
        target("**/src/**/*.yaml")
        jackson()
    }
}

tasks {
    getByName("spotlessKotlinGradle").dependsOn("spotlessKotlin")
    getByName("spotlessSql").dependsOn("spotlessKotlinGradle")
    getByName("spotlessJson").dependsOn("spotlessKotlinGradle")
    getByName("spotlessYaml").dependsOn("spotlessKotlinGradle")
}
