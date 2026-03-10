rootProject.name = "toy-msa"

include(
    ":apps:service-discovery",
    ":apps:config-service",
    ":apps:apigateway-service",
    ":apps:user-service",
    ":apps:order-service",
    ":apps:catalog-service",
    ":apps:first-service",
    ":apps:second-service",
    ":modules:data-serializer",
    ":modules:event",
    ":modules:jpa",
    ":supports:logging",
    ":supports:monitoring",
)

pluginManagement {
    val kotlinVersion: String by settings
    val ktLintPluginVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    repositories {
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
        gradlePluginPortal()
    }

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.jetbrains.kotlin.jvm" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.kapt" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)
                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktLintPluginVersion)
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}
