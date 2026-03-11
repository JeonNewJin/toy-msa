plugins {
    id("org.jetbrains.kotlin.plugin.jpa")
}

dependencies {
    // add-ons
    implementation(project(":modules:event"))
    implementation(project(":modules:jpa"))
    implementation(project(":modules:kafka"))
    implementation(project(":supports:logging"))
    implementation(project(":supports:monitoring"))
    implementation(project(":modules:grpc"))

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // Spring Cloud
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.cloud:spring-cloud-starter-kubernetes-client-loadbalancer")

    // Test Fixtures
    testImplementation(testFixtures(project(":modules:jpa")))
}
