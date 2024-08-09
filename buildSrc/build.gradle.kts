import org.gradle.kotlin.dsl.`kotlin-dsl`

plugins {
    `kotlin-dsl`
    id("groovy-gradle-plugin")
}

dependencies {
    implementation(libs.openapi.gradle)
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

repositories {
    mavenCentral()
}
