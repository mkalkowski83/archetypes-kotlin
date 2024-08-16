import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
}

dependencies {
    // Spring Boot
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)

    // Database
    runtimeOnly(libs.postgresql)
    implementation(libs.bundles.flyway)
    implementation(libs.hikaricp)

    // Logs
    runtimeOnly(libs.logstash.encoder)

    // Tests
    testImplementation(libs.bundles.tests)
}

tasks {
    getByName<Jar>("jar") {
        enabled = false
    }

    getByName<BootJar>("bootJar") {
        enabled = true
    }

    withType<Test> {
        useJUnitPlatform()
    }

    build {
        dependsOn("copyAllDependencies")
    }

    register<Copy>("copyAllDependencies") {
        val buildDir = layout.buildDirectory.asFile.get()

        project.delete(
            fileTree("$buildDir/output/libs"),
        )
    }
}
