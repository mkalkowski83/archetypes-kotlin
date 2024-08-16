import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.spotless) apply true
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
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

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    kotlin {
        target(
            fileTree(".") {
                include("**/*.kt")
                exclude(
                    "**/.gradle/**",
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
                exclude("**/.gradle/**", "**/build/generated/**", "**/buildSrc/build/**")
            },
        )
        trimTrailingWhitespace()
        ktlint()
            .setEditorConfigPath("$rootDir/.editorconfig")
    }
}

tasks {
    getByName("spotlessKotlinGradle").dependsOn("spotlessKotlin")
}
