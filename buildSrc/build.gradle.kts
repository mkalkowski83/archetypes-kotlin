plugins {
    `kotlin-dsl`
    id("groovy-gradle-plugin")
}

dependencies {
    implementation(files(libs.javaClass.superclass.protectionDomain.codeSource.location))
}

repositories {
    mavenCentral()
}
