import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    alias(libs.plugins.spring.boot)
    alias(libs.plugins.spring.dependency.management)
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.spring)
    alias(libs.plugins.jooq.docker)
}

dependencies {
    // Spring Boot
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.validation)
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.spring.boot.starter.jooq)

    // Kotlin
    implementation(libs.bundles.jackson)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.reactor)

    // Database
    runtimeOnly(libs.postgresql)
    implementation(libs.bundles.flyway)
    implementation(libs.hikaricp)
    jooqCodegen(libs.postgresql)

    // Observability
    compileOnly(libs.opentelemetry.javaagent)

    // Logs
    runtimeOnly(libs.logstash.encoder)

    // Utils
    implementation(libs.utils.serializer)

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

    generateJooqClasses {
        basePackageName.set("com.sumup.os.apptemplate.entities")
        includeFlywayTable.set(false)
        flywayProperties.put("flyway.postgresql.transactional.lock", "false")
    }

    register<Copy>("copyAllDependencies") {
        val buildDir = layout.buildDirectory.asFile.get()

        project.delete(
            fileTree("$buildDir/output/libs"),
        )

        from(configurations.compileClasspath)
        into("$buildDir/output/libs")
        include("opentelemetry-javaagent*")
        rename("-[1-9]+.[0-9]+.[0-9]+.jar", ".jar")
    }
}
