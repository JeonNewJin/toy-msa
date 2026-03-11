plugins {
    id("java-test-fixtures")
}

dependencies {
    // Kafka
    api("org.springframework.boot:spring-boot-starter-kafka")

    // Serde
    implementation("com.fasterxml.jackson.core:jackson-databind")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // Test
    testImplementation("org.springframework.kafka:spring-kafka-test")
    testImplementation("org.testcontainers:testcontainers-kafka")
    testFixturesImplementation(platform("org.springframework.boot:spring-boot-dependencies:${project.properties["springBootVersion"]}"))
    testFixturesImplementation("org.testcontainers:testcontainers-kafka")
}
