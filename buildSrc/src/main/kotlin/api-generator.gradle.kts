import org.openapitools.generator.gradle.plugin.tasks.GenerateTask as GenerateOpenApiTask

plugins {
    id("org.openapi.generator")
}

val buildTmpDir = "$projectDir/buildSrc/.tmp"
val destinationDir = "$projectDir/os-app-template"
val templatesDir = "$projectDir/buildSrc/templates"
val osDocsRepositoryUrl = "git@github.com:sumup/os-docs.git"
val apiDistFile = "$buildTmpDir/os-docs/dist/os-app-template.yaml"
val apiNamespace = "com.sumup.os.app.template.infrastructure.api"
val branch = "main"

tasks {
    register("fetchOsDocsRepository") {
        description = "Fetches os-docs repository with OpenAPI definitions"
        group = "openapi tools"

        exec {
            commandLine("rm", "-rf", "$buildTmpDir/os-docs")
        }

        exec {
            commandLine("git", "clone", "-b", branch, osDocsRepositoryUrl, "$buildTmpDir/os-docs")
        }
    }

    register("cleanGeneratedApi") {
        description = "Removes generated OpenAPI code"
        group = "openapi tools"

        doLast {
            logger.info("Cleaning up generated code")
            exec {
                commandLine("rm", "-rf", buildTmpDir)
            }
            File("$destinationDir/docs").deleteRecursively()
            File("$destinationDir/.openapi-generator").deleteRecursively()
            File("$destinationDir/src/main/kotlin/org").deleteRecursively()
            File("$destinationDir/src/main/kotlin/com/sumup/os/app/template/infrastructure/api/ApiUtil.kt").deleteRecursively()
            File("$destinationDir/.openapi-generator-ignore").deleteRecursively()
            File("$destinationDir/pom.xml").deleteRecursively()
            File("$destinationDir/README.md").deleteRecursively()

            // Move as a dependency after spotless will be working on gradle 8
            // https://github.com/diffplug/spotless/issues/1572
            exec {
                commandLine("./gradlew", ":spotlessKotlinApply")
            }
        }
    }

    register("generateApi", GenerateOpenApiTask::class) {
        dependsOn("fetchOsDocsRepository")

        group = "openapi tools"

        val config =
            mapOf(
                "interfaceOnly" to "true",
                "implicitHeaders" to "true",
                "hideGenerationTimestamp" to "true",
                "useTags" to "true",
                "gradleBuildFile" to "false",
                "reactive" to "true",
                "documentationProvider" to "none",
                "annotationLibrary" to "none",
                "enumPropertyNaming" to "UPPERCASE",
                "generateApiDocumentation" to "false",
                "useSpringBoot3" to "true",
            )

        generatorName.set("kotlin-spring")
        inputSpec.set(apiDistFile)
        outputDir.set(destinationDir)
        templateDir.set(templatesDir)
        apiPackage.set(apiNamespace)
        modelPackage.set("$apiNamespace.models")
        configOptions.set(config)

        finalizedBy("cleanGeneratedApi")
    }
}
