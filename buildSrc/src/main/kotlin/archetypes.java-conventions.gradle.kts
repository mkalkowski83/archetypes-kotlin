plugins {
    id("java")
}

repositories { mavenCentral() }

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }

    withJavadocJar()
    withSourcesJar()
}
